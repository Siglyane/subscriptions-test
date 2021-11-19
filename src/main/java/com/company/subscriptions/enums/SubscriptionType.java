package com.company.subscriptions.enums;

import com.company.subscriptions.model.EventHistory;

import java.util.Objects;

public enum SubscriptionType {

    /*
     * Usei uma enum por ser somente 3 possíveis inscrições
     */
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
