package com.rmaciel.nicalerts.monitoring.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Getter
public class AlertHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    @NonNull
    private Device device;

    @NonNull
    @Enumerated(EnumType.STRING)
    private AlertStatus status;

    private LocalDateTime dateTime = LocalDateTime.now();
    private String notes;

    public boolean isAvailable() {
        return status == AlertStatus.AVAILABLE;
    }
}
