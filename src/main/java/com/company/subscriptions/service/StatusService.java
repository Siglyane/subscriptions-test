package com.company.subscriptions.service;

import com.company.subscriptions.model.Status;
import com.company.subscriptions.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    public Status createStatus() {
        Status status = new Status();
        status.setName("ATIVA");
        statusRepository.save(status);
        return status;
    }
}
