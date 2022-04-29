package com.rmaciel.nicalerts.monitoring.controllers;

import com.rmaciel.nicalerts.monitoring.models.AlertHistory;
import com.rmaciel.nicalerts.monitoring.models.AlertStatus;
import com.rmaciel.nicalerts.monitoring.models.Device;
import com.rmaciel.nicalerts.monitoring.services.NICDeviceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

@RestController
public class MonitoringController {
    private final NICDeviceService nicDeviceService;

    public MonitoringController(NICDeviceService nicDeviceService) {
        this.nicDeviceService = nicDeviceService;
    }

    @GetMapping("/update-monitoring")
    public String updateMonitoringDevices() throws UnknownHostException {
        nicDeviceService.checkMonitoredDevices();

        return "Devices monitored has been updated";
    }
}
