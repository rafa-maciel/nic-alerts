package com.rmaciel.nicalerts.monitoring.repositories;

import com.rmaciel.nicalerts.monitoring.models.AlertHistory;
import org.springframework.data.repository.CrudRepository;

public interface AlertHistoryRepository extends CrudRepository<AlertHistory, Long> {
}
