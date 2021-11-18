package com.company.subscriptions.DTO;

import com.company.subscriptions.model.Status;


public class StatusDTO {


    private String name;

    public StatusDTO(Status entity) {
        this.name = entity.getName();
    }

    public StatusDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
