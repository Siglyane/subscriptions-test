package com.company.subscriptions.repository;

import com.company.subscriptions.model.EventHistory;
import com.company.subscriptions.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventHistoryRepository extends JpaRepository<EventHistory, Integer> {

    Optional<EventHistory> findBySubscriptionId(Subscription subscription);



}
