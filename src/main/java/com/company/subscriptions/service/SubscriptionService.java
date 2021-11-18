package com.company.subscriptions.service;


import com.company.subscriptions.model.EventHistory;
import com.company.subscriptions.model.Status;
import com.company.subscriptions.model.Subscription;
import com.company.subscriptions.repository.StatusRepository;
import com.company.subscriptions.repository.SubscriptionRepository;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    StatusService statusService;

    public Subscription saveSubscription(Subscription subscription, EventHistory eventHistory){
        Subscription newSubscription = new Subscription();
        newSubscription.setId(subscription.getId());
        newSubscription.setCreatedAt(LocalDateTime.now());
        newSubscription.setUpdatedAt(LocalDateTime.now());
        Status status = statusService.createStatus(eventHistory);
        newSubscription.setStatusId(status);
        subscriptionRepository.save(newSubscription);
        return newSubscription;

    }

    public void updateTimeStamp(Subscription subscription) {
        Optional<Subscription> subscriptionRequested = subscriptionRepository.findById(subscription.getId());
        subscriptionRequested.get().setUpdatedAt(LocalDateTime.now());
    }
}
