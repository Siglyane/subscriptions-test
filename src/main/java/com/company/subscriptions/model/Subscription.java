package com.company.subscriptions.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status statusId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany
    @JoinColumn(name = "subscription_id")
    private List<EventHistory> eventHistories;

    public Subscription(String id, Status statusId) {
        this.id = id;
        this.statusId = statusId;
    }

    public List<EventHistory> getEventHistories() {
        return eventHistories;
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
