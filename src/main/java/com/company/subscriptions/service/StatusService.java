package com.company.subscriptions.service;

import com.company.subscriptions.enums.SubscriptionType;
import com.company.subscriptions.model.EventHistory;
import com.company.subscriptions.model.Status;
import com.company.subscriptions.model.Subscription;
import com.company.subscriptions.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    public Status createStatus(EventHistory eventHistory) {
        Status status = new Status();
        status.setName(eventHistory.getType().getValue());
        statusRepository.save(status);
        return status;
    }

    public void statusCanceled(Subscription subscriptionId) {
        Status status = subscriptionId.getStatusId();
        status.setName(SubscriptionType.SUBSCRIPTION_CANCELED.getValue());
    }

    public void statusRestarted(Subscription subscriptionId) {
        Status status = subscriptionId.getStatusId();
        status.setName(SubscriptionType.SUBSCRIPTION_RESTARTED.getValue());
    }
}
