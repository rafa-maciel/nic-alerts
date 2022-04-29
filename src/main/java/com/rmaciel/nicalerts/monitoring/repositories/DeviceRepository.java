package com.rmaciel.nicalerts.monitoring.repositories;

import com.rmaciel.nicalerts.monitoring.models.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Integer> {
    Iterable<Device> findByIsMonitoredTrue();
}
