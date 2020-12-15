package com.services.mediator.controllers.rest;

import com.services.mediator.entities.Training;
import com.services.mediator.entities.dto.TrainingDTO;
import com.services.mediator.entities.dto.VetDTO;
import com.services.mediator.services.TrainingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("trainings")
@RequiredArgsConstructor
public class TrainingController {
    private final TrainingService trainingService;

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.training.routingKey}")
    private String routingKey;

    @GetMapping
    public ResponseEntity<Training[]> showTrainings() {
        return trainingService.showTrainings();
    }

    @GetMapping("{id}")
    public ResponseEntity<Training> showTrainingById(@PathVariable UUID id){
        return trainingService.showTrainingById(id);
    }

    @PostMapping
    public ResponseEntity<Training> addTraining(@RequestBody TrainingDTO trainingDTO){
        return trainingService.addTraining(trainingDTO);
    }

    @PostMapping("rabbitmq")
    public String addTrainingMQ(@RequestBody TrainingDTO trainingDTO) {
        rabbitTemplate.convertAndSend(exchange,routingKey,trainingDTO);
        return "I've added training successfully";
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable UUID id){
        return trainingService.deleteTraining(id);
    }

}
