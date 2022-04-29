package com.rmaciel.nicalerts.monitoring.config.schedule;

import com.rmaciel.nicalerts.monitoring.services.NICDeviceService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskMonitoreDevices {
    private final NICDeviceService nicDeviceService;
    private static final int MONITORING_DELAY = 300000; // 5 minutes

    public TaskMonitoreDevices(NICDeviceService nicDeviceService) {
        this.nicDeviceService = nicDeviceService;
    }

    @Scheduled(fixedRate = MONITORING_DELAY)
    public void checkMonitoredDevices() {
        nicDeviceService.checkMonitoredDevices();
    }
}
