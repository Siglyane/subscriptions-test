package com.company.subscriptions.service;


import com.company.subscriptions.model.EventHistory;
import com.company.subscriptions.model.Status;
import com.company.subscriptions.model.Subscription;
import com.company.subscriptions.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    StatusService statusService;

    // Salva uma assinatura
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

    // Atualiza a hora do update
    public void updateTimeStamp(Subscription subscription) {
        Optional<Subscription> subscriptionRequested = subscriptionRepository.findById(subscription.getId());
        Subscription subscriptionToUpdate = subscriptionRequested.get();
        subscriptionToUpdate.setUpdatedAt(LocalDateTime.now());
        subscriptionRepository.save(subscriptionToUpdate);
    }
}
