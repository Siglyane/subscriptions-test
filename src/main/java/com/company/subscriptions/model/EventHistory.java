package com.company.subscriptions.model;

import com.company.subscriptions.enums.SubscriptionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "event_history")
public class EventHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // O Enumerated informa o nome do enum para o banco de dados
    // JsonProperty é como a informação ira aparecer e ser buscada no json
    @Column
    @Enumerated(EnumType.STRING)
    @JsonProperty("notification_type")
    private SubscriptionType type;

    // Informa relação de eventos com subs id
    @ManyToOne
    @JsonProperty("subscription")
    @JoinColumn(name = "subscription_id", unique = true)
    private Subscription subscriptionId;

    // Formata a data no JSON, acho mais legível para o cliente e não afeta a formatação do banco de dados
    @JsonFormat(pattern="dd-MM-yyyy hh:mm:ss")
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public EventHistory() {
    }

    public EventHistory(EventHistory eventHistory){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setSubscriptionId(Subscription subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
