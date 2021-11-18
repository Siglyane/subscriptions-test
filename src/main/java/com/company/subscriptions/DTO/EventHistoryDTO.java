package com.company.subscriptions.DTO;


import com.company.subscriptions.enums.SubscriptionType;
import com.company.subscriptions.model.EventHistory;
import com.company.subscriptions.model.Subscription;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class EventHistoryDTO {


    @JsonProperty("notification_type")
    private SubscriptionType type;

    @JsonProperty("subscription")
    private Subscription subscriptionId;


    public EventHistoryDTO(EventHistory entity) {
        this.type = entity.getType();
        this.subscriptionId = entity.getSubscriptionId();
    }

    public EventHistoryDTO(SubscriptionType type, Subscription subscriptionId) {
        this.type = type;
        this.subscriptionId = subscriptionId;
    }


    public SubscriptionType getType() {
        return type;
    }

    public void setType(SubscriptionType type) {
        this.type = type;
    }

    public Subscription getSubscriptionId() {
        return subscriptionId;
    }

}
