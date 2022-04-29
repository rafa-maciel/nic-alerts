package com.rmaciel.nicalerts.monitoring.services;

import com.rmaciel.nicalerts.monitoring.infra.services.NetworkCommunicationService;
import com.rmaciel.nicalerts.monitoring.models.AlertHistory;
import com.rmaciel.nicalerts.monitoring.models.AlertStatus;
import com.rmaciel.nicalerts.monitoring.models.Device;
import com.rmaciel.nicalerts.monitoring.repositories.AlertHistoryRepository;
import com.rmaciel.nicalerts.monitoring.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NICDeviceService {
    private final DeviceRepository repository;
    private final AlertHistoryRepository alertHistoryRepository;
    private final NetworkCommunicationService nicService;

    public NICDeviceService(DeviceRepository repository, AlertHistoryRepository alertHistoryRepository) {
        this.repository = repository;
        this.alertHistoryRepository = alertHistoryRepository;
        this.nicService = new NetworkCommunicationService();
    }


    public void checkMonitoredDevices() {
        Iterable<Device> monitoredDevices = repository.findByIsMonitoredTrue();
        List<AlertHistory> changedAlerts = new ArrayList<>();
        List<Device> changedDevices = new ArrayList<>();

        monitoredDevices.forEach(device -> {
            AlertHistory alertHistory = currentAlertFrom(device);
            if (device.hasStatusChange(alertHistory)) {
                device.updateFromAlert(alertHistory);
                changedDevices.add(device);
                changedAlerts.add(alertHistory);
            }
        });

        repository.saveAll(changedDevices);
        alertHistoryRepository.saveAll(changedAlerts);
    }

    private AlertHistory currentAlertFrom(Device device) {
        AlertStatus status;

        try {
            Boolean isReachable = nicService.ping(device.getIp());
            status = isReachable ? AlertStatus.AVAILABLE : AlertStatus.UNAVAILABLE;
        } catch (UnknownHostException e) {
            status = AlertStatus.UNKNOWN_HOST;
        }

        return new AlertHistory(device, status);
    }
}
