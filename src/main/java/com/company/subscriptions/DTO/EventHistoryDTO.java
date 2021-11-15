package com.company.subscriptions.DTO;


import com.company.subscriptions.model.EventHistory;
import com.company.subscriptions.model.Subscription;

import java.time.LocalDateTime;

public class EventHistoryDTO {

    private Integer id;


    private String type;


    private Subscription subscriptionId;


    private LocalDateTime createdAt;

    public EventHistoryDTO(EventHistory entity) {
        this.type = entity.getType();
        this.subscriptionId = entity.getSubscriptionId();
    }

    public EventHistoryDTO(String type, Subscription subscriptionId) {
        this.type = type;
        this.subscriptionId = subscriptionId;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Subscription getSubscriptionId() {
        return subscriptionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
