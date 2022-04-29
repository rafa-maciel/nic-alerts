package com.rmaciel.nicalerts.monitoring.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private String ip;

    @NonNull
    private Boolean isMonitored;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "type_id")
    private DeviceType type;

    @NonNull
    private String tags;

    @Enumerated(EnumType.STRING)
    private AlertStatus lastStatus;

    private LocalDateTime lastDateTime;

    public void updateFromAlert(AlertHistory alert) {
        this.lastDateTime = alert.getDateTime();
        this.lastStatus = alert.getStatus();
    }

    public boolean hasStatusChange(AlertHistory alert) {
        return this.lastStatus != alert.getStatus();
    }
}
