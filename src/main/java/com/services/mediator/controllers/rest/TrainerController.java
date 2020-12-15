package com.services.mediator.controllers.rest;


import com.services.mediator.entities.Trainer;
import com.services.mediator.entities.dto.GroomDTO;
import com.services.mediator.entities.dto.TrainerDTO;
import com.services.mediator.services.TrainerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("trainers")
@RequiredArgsConstructor
public class TrainerController {
    private final TrainerService trainerService;

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.trainer.routingKey}")
    private String routingKey;

    @GetMapping
    public ResponseEntity<Trainer[]> showTrainers() {
        return trainerService.showTrainers();
    }

    @GetMapping("{id}")
    public ResponseEntity<Trainer> showTrainerById(@PathVariable UUID id){
        return trainerService.showTrainerById(id);
    }


    @PostMapping
    public ResponseEntity<Trainer> addTrainer(@RequestBody TrainerDTO trainerDTO) {
        return trainerService.addTrainer(trainerDTO);
    }

    @PostMapping("rabbitmq")
    public String addTrainerMQ(@RequestBody TrainerDTO trainerDTO) {
        rabbitTemplate.convertAndSend(exchange,routingKey,trainerDTO);
        return "I've added trainer successfully";
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable UUID id){
        return trainerService.deleteTrainer(id);
    }
}
