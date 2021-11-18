package com.company.subscriptions.enums;

public enum SubscriptionType {

    SUBSCRIPTION_PURCHASED("PURCHASED"),
    SUBSCRIPTION_CANCELED("CANCELED"),
    SUBSCRIPTION_RESTARTED("RESTARTED");

    private String value;

    SubscriptionType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
