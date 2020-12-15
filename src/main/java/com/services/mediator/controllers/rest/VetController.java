package com.services.mediator.controllers.rest;

import com.services.mediator.entities.Vet;
import com.services.mediator.entities.dto.GroomDTO;
import com.services.mediator.entities.dto.VetDTO;
import com.services.mediator.services.VetService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("vets")
@RequiredArgsConstructor
public class VetController {
    private final VetService vetService;

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.vet.routingKey}")
    private String routingKey;

    @GetMapping
    public ResponseEntity<Vet[]> showVets() {
        return vetService.getVets();
    }

    @GetMapping("{id}")
    public ResponseEntity<Vet> showVetById(@PathVariable UUID id) {
        return vetService.getVetById(id);
    }

    @PostMapping
    public ResponseEntity<Vet> addVet(@RequestBody VetDTO vetDTO) {
        return vetService.addVet(vetDTO);
    }

    @PostMapping("rabbitmq")
    public String addVetMQ(@RequestBody VetDTO vetDTO) {
        rabbitTemplate.convertAndSend(exchange,routingKey,vetDTO);
        return "I've added vet successfully";
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVet(@PathVariable UUID id){
        return vetService.deleteVet(id);
    }
}