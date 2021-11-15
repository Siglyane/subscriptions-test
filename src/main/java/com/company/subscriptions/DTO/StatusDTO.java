package com.company.subscriptions.DTO;

import com.company.subscriptions.model.Status;


public class StatusDTO {


    private Integer id;


    private String name;

    public StatusDTO(Status entity) {
        this.name = entity.getName();
    }

    public StatusDTO(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
