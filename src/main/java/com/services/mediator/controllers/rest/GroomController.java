package com.services.mediator.controllers.rest;

import com.services.mediator.entities.Groom;
import com.services.mediator.entities.dto.ClientDTO;
import com.services.mediator.entities.dto.GroomDTO;
import com.services.mediator.services.GroomService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("grooms")
@RequiredArgsConstructor
public class GroomController {
    private final GroomService groomService;

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.groom.routingKey}")
    private String routingKey;

    @GetMapping
    public ResponseEntity<Groom[]> showGrooms() {
        return groomService.showGrooms();
    }

    @GetMapping("{id}")
    public ResponseEntity<Groom> showGroomById(@PathVariable UUID id){
        return groomService.getGroomById(id);
    }

    @PostMapping
    public ResponseEntity<Groom> addGroom(@RequestBody GroomDTO groomDTO) {
        return groomService.addGroom(groomDTO);
    }

    @PostMapping("rabbitmq")
    public String addGroomMQ(@RequestBody GroomDTO groomDTO) {
        rabbitTemplate.convertAndSend(exchange,routingKey,groomDTO);
        return "I've added groom successfully";
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGroom(@PathVariable UUID id){
        return groomService.deleteGroom(id);
    }
}
