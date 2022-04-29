package com.rmaciel.nicalerts.monitoring.repositories;

import com.rmaciel.nicalerts.monitoring.models.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Integer> {
}
