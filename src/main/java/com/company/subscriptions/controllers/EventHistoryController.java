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
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/history")
public class EventHistoryController {

    @Autowired
    EventHistoryService eventHistoryService;



    // Retorna uma lista com todos os objetos salvos no banco de dados
    @GetMapping
    public ResponseEntity<List<EventHistory>> getAllEvents() {
        List<EventHistory> events = eventHistoryService.findAll();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // Cria um novo objeto
    @PostMapping("")
    public ResponseEntity<EventHistory> newSubscription (@RequestBody EventHistoryDTO eventHistoryDTO) {
        try {
            /* Verifica se já existe um objeto com essa Id no banco de dados
             * Como este atributo possui a anotação unique = true, mesmo sem esse verificação
             * ele já não possibilitava a criação de um novo objeto, porém o retorno do HTTP era 500
             * fiz esta verificação para usar um retorno adequado.
             */
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


    /*
     * Para a solicitação de alterar o status optei por usar o Patch, que determina a alteração de parte do objeto,
     *  porém algumas empresas costumam usar o Put e criar um novo objeto mantendo as informações antigas e
     *  usando as novas informações na hora de criar um novo objeto. Escolhi usar o Patch por ser
     *  uma aplicação pequena e não afetar muito no tempo de resposta.
     */

    //Cancela a inscrição sem deletar do banco
    @PatchMapping("/cancel")
    public ResponseEntity<EventHistory> cancelSubscription (@RequestBody EventHistoryDTO eventHistoryDTO) {
        try{
            //Verica se existe no banco e se o tipo solicitado está de acordo com o end point
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


    // Recompra
    @PatchMapping("/restart")
    public ResponseEntity<EventHistory> restartSubscription (@RequestBody EventHistoryDTO eventHistoryDTO) {
        try {
            //Verica se existe no banco, se a assinatura foi cancelada e se o tipo solicitado está
            // de acordo com o end point
           Optional<EventHistory> eventRequested = eventHistoryService.findById(eventHistoryDTO.getSubscriptionId());
            if (eventHistoryDTO.getType() == SubscriptionType.SUBSCRIPTION_RESTARTED
                    && eventRequested.isPresent()
                    && eventRequested.get().getType().equals(SubscriptionType.SUBSCRIPTION_CANCELED)) {
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
