package com.company.subscriptions.repository;

import com.company.subscriptions.model.EventHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventHistoryRepository extends JpaRepository<EventHistory, Integer> {

}
