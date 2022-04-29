package com.rmaciel.nicalerts.monitoring.config.database;

import com.rmaciel.nicalerts.monitoring.models.Device;
import com.rmaciel.nicalerts.monitoring.models.DeviceType;
import com.rmaciel.nicalerts.monitoring.models.Location;
import com.rmaciel.nicalerts.monitoring.repositories.AlertHistoryRepository;
import com.rmaciel.nicalerts.monitoring.repositories.DeviceRepository;
import com.rmaciel.nicalerts.monitoring.repositories.DeviceTypeRepository;
import com.rmaciel.nicalerts.monitoring.repositories.LocationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Profile("dbtest")
public class DatabaseInit implements CommandLineRunner {
    private final LocationRepository locationRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceTypeRepository deviceTypeRepository;
    private final AlertHistoryRepository alertHistoryRepository;

    public DatabaseInit(LocationRepository locationRepository, DeviceRepository deviceRepository, DeviceTypeRepository deviceTypeRepository, AlertHistoryRepository alertHistoryRepository) {
        this.locationRepository = locationRepository;
        this.deviceRepository = deviceRepository;
        this.deviceTypeRepository = deviceTypeRepository;
        this.alertHistoryRepository = alertHistoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Location locStore = locationRepository.save(
                new Location(null, "Store", "Device Store")
        );

        Location locWarehouse = locationRepository.save(
                new Location(null, "Warehouse", "Devices Warehouse")
        );

        DeviceType core_switch = deviceTypeRepository.save(
                new DeviceType(null, "Core Switch")
        );

        DeviceType access_point = deviceTypeRepository.save(
                new DeviceType(null, "Access Point")
        );

        deviceRepository.saveAll(Arrays.asList(
                new Device("Main Store Core", "Main Core",
                        "10.114.82.56", true, locWarehouse, core_switch, "CCA"),
                new Device("Cook Room AP", "Cook Room AP",
                        "179.167.227.127", true, locStore, access_point, "Store CookRoom")
        ));
    }
}
