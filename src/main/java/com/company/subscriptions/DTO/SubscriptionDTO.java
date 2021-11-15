package com.company.subscriptions.DTO;

import com.company.subscriptions.model.Status;
import com.company.subscriptions.model.Subscription;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

public class SubscriptionDTO {

    private String id;

    private Status statusId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public SubscriptionDTO(Subscription entity) {
        this.id = entity.getId();
        this.statusId = entity.getStatusId();
    }

    public SubscriptionDTO(String id, Status statusId) {
        this.id = id;
        this.statusId = statusId;
    }

    public String getId() {
        return id;
    }


    public Status getStatusId() {
        return statusId;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}