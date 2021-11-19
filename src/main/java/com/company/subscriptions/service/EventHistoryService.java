package com.company.subscriptions.service;

import com.company.subscriptions.enums.SubscriptionType;
import com.company.subscriptions.model.EventHistory;
import com.company.subscriptions.model.Subscription;
import com.company.subscriptions.repository.EventHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventHistoryService {

    @Autowired
    EventHistoryRepository eventHistoryRepository;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    StatusService statusService;


    // Cria e salva um evento, gerando também um assinatura e um status
    public EventHistory saveSubscription(EventHistory eventHistory) {
        EventHistory newEvent = new EventHistory(eventHistory);
        newEvent.setSubscriptionId(eventHistory.getSubscriptionId());
        newEvent.setType(eventHistory.getType());
        newEvent.setCreatedAt(LocalDateTime.now());
        Subscription sub = subscriptionService.saveSubscription(newEvent.getSubscriptionId(), newEvent);
        newEvent.setSubscriptionId(sub);
        eventHistoryRepository.save(newEvent);
        return newEvent;
    }

    // Encontra todos os eventos
    public List<EventHistory> findAll() {
        List<EventHistory> events = new ArrayList<>(eventHistoryRepository.findAll());
        return events;
    }

    // Procura pela id a assinatura
    public Optional<EventHistory> findById(Subscription subscriptionId) {
        Optional<EventHistory> eventRequested = eventHistoryRepository.findBySubscriptionId(subscriptionId);
        return eventRequested;
    }

    /*
     *
    public void deleteSubscription(Optional<EventHistory> eventRequested) {
        eventHistoryRepository.delete(eventRequested.get());
    }
     */

    // Faz o cancelamento: atualizando o status, hora que foi atualizada e tipo da assinatura
    public void cancelSubscription(Optional<EventHistory> eventRequested) {
        EventHistory eventToCancel = eventRequested.get();
        subscriptionService.updateTimeStamp(eventToCancel.getSubscriptionId());
        statusService.statusCanceled(eventToCancel.getSubscriptionId());
        eventToCancel.setType(SubscriptionType.SUBSCRIPTION_CANCELED);
        eventHistoryRepository.save(eventToCancel);
    }

    // Faz a recompra: atualizando o status, hora que foi atualizada e tipo da assinatura
    public void restartSubscription(Optional<EventHistory> eventRequested) {
        EventHistory eventToRestart = eventRequested.get();
        statusService.statusRestarted(eventToRestart.getSubscriptionId());
        subscriptionService.updateTimeStamp(eventToRestart.getSubscriptionId());
        eventToRestart.setType(SubscriptionType.SUBSCRIPTION_RESTARTED);
        eventHistoryRepository.save(eventToRestart);
    }
}
