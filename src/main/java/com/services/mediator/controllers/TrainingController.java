package com.services.mediator.controllers;

import com.services.mediator.entities.Client;
import com.services.mediator.entities.Training;
import com.services.mediator.entities.dto.TrainingDTO;
import com.services.mediator.services.TrainingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@RequestMapping("trainings")
@AllArgsConstructor
public class TrainingController {
    private final TrainingService trainingService;

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

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable UUID id){
        return trainingService.deleteTraining(id);
    }

}
