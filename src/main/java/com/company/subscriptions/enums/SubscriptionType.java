package com.company.subscriptions.enums;

import com.company.subscriptions.model.EventHistory;

import java.util.Objects;

public enum SubscriptionType {

    SUBSCRIPTION_PURCHASED("ATIVA"),
    SUBSCRIPTION_CANCELED("CANCELADA"),
    SUBSCRIPTION_RESTARTED("ATIVA");

    private String value;

    SubscriptionType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }




}
