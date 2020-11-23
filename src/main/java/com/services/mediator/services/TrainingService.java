package com.services.mediator.services;

import com.services.mediator.entities.Training;
import com.services.mediator.entities.dto.TrainingDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class TrainingService {
    private static final String TRAINING_URL = "http://trainingservice:8088/trainings";
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    public ResponseEntity<Training[]> showTrainings() {
        return restTemplate.exchange(TRAINING_URL, HttpMethod.GET, headersEntity, Training[].class);
    }

    public ResponseEntity<Training> showTrainingById(UUID id){
        return restTemplate.exchange(TRAINING_URL+"/"+id, HttpMethod.GET, headersEntity, Training.class);
    }

    public ResponseEntity<Training> addTraining(TrainingDTO trainingDTO){
        HttpEntity<TrainingDTO> training = new HttpEntity<>(trainingDTO);
        return restTemplate.exchange(TRAINING_URL, HttpMethod.POST, training, Training.class);
    }

    public ResponseEntity<Void> deleteTraining(UUID id){
        restTemplate.exchange(TRAINING_URL + "/"+id, HttpMethod.DELETE,
                headersEntity, Void.class);
        return ResponseEntity.noContent().build();
    }
}
