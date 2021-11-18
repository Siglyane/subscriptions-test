package com.company.subscriptions.controllers;

import com.company.subscriptions.DTO.EventHistoryDTO;
import com.company.subscriptions.model.EventHistory;
import com.company.subscriptions.service.EventHistoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/history")
public class EventHistoryController {

    @Autowired
    EventHistoryService eventHistoryService;



    @GetMapping
    public ResponseEntity<List<EventHistory>> getAllEvents() {
        List<EventHistory> events = eventHistoryService.findAll();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<EventHistory> newSubscription (@RequestBody EventHistoryDTO eventHistoryDTO) {
        EventHistory eventHistory = new EventHistory();
        BeanUtils.copyProperties(eventHistoryDTO, eventHistory);
        eventHistory = eventHistoryService.saveSubscription(eventHistory);
        return new ResponseEntity<>(eventHistory, HttpStatus.CREATED);
    }

    @DeleteMapping("")
    public ResponseEntity<EventHistory> deleteSubscription (@RequestBody EventHistoryDTO eventHistoryDTO) {
        EventHistory eventHistory = new EventHistory();
        BeanUtils.copyProperties(eventHistoryDTO, eventHistory);
        Optional<EventHistory> eventRequested = eventHistoryService.findById(eventHistory.getSubscriptionId());
        eventHistoryService.deleteSubscription(eventRequested);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("")
    public ResponseEntity<EventHistory> cancelSubscription (@RequestBody EventHistoryDTO eventHistoryDTO) {
        EventHistory eventHistory = new EventHistory();
        BeanUtils.copyProperties(eventHistoryDTO, eventHistory);
        Optional<EventHistory> eventRequested = eventHistoryService.findById(eventHistory.getSubscriptionId());
        eventHistoryService.cancelSubscription(eventRequested);
        return new ResponseEntity<>(eventRequested.get(), HttpStatus.OK);
    }


//    @PatchMapping("")
//    public ResponseEntity<EventHistory> restartSubscription (@RequestBody EventHistoryDTO eventHistoryDTO) {
//
//    }
}
