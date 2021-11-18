package com.company.subscriptions.controllers;

import com.company.subscriptions.DTO.EventHistoryDTO;
import com.company.subscriptions.enums.SubscriptionType;
import com.company.subscriptions.model.EventHistory;
import com.company.subscriptions.service.EventHistoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        try {
            Optional<EventHistory> eventRequested = eventHistoryService.findById(eventHistoryDTO.getSubscriptionId());
            if (eventHistoryDTO.getType() == SubscriptionType.SUBSCRIPTION_PURCHASED && eventRequested.isEmpty()) {
                EventHistory eventHistory = new EventHistory();
                BeanUtils.copyProperties(eventHistoryDTO, eventHistory);
                eventHistory = eventHistoryService.saveSubscription(eventHistory);
                return new ResponseEntity<>(eventHistory, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Ao fazer a opção de cancelamento comecei a trabalhar no método HTTP delete, porém escolhi comentar
     * o código e fazer através do um Patch pois para funcionar dentro do comportamento esperado do
     * método não ficaria de acordo com a funcionalidade solicitada.
     *
    @DeleteMapping("")
    public ResponseEntity<EventHistory> deleteSubscription (@RequestBody EventHistoryDTO eventHistoryDTO) {
        EventHistory eventHistory = new EventHistory();
        BeanUtils.copyProperties(eventHistoryDTO, eventHistory);
        Optional<EventHistory> eventRequested = eventHistoryService.findById(eventHistory.getSubscriptionId());
        eventHistoryService.deleteSubscription(eventRequested);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    *
    */

    @PatchMapping("/cancel")
    public ResponseEntity<EventHistory> cancelSubscription (@RequestBody EventHistoryDTO eventHistoryDTO) {
        try{
            Optional<EventHistory> eventRequested = eventHistoryService.findById(eventHistoryDTO.getSubscriptionId());
            if (eventHistoryDTO.getType() == SubscriptionType.SUBSCRIPTION_CANCELED && eventRequested.isPresent())
            {
                EventHistory eventHistory = new EventHistory();
                BeanUtils.copyProperties(eventHistoryDTO, eventHistory);
                eventHistoryService.cancelSubscription(eventRequested);
                return new ResponseEntity<>(eventRequested.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/restart")
    public ResponseEntity<EventHistory> restartSubscription (@RequestBody EventHistoryDTO eventHistoryDTO) {
        try {
           Optional<EventHistory> eventRequested = eventHistoryService.findById(eventHistoryDTO.getSubscriptionId());
            if (eventHistoryDTO.getType() == SubscriptionType.SUBSCRIPTION_RESTARTED && eventRequested.isPresent()) {
                EventHistory eventHistory = new EventHistory();
                BeanUtils.copyProperties(eventHistoryDTO, eventHistory);
                eventHistoryService.restartSubscription(eventRequested);
                return new ResponseEntity<>(eventRequested.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
