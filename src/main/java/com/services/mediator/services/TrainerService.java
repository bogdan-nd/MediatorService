package com.services.mediator.services;

import com.services.mediator.entities.Client;
import com.services.mediator.entities.Trainer;
import com.services.mediator.entities.dto.TrainerDTO;
import com.services.mediator.entities.enums.SportsCategory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class TrainerService {
    private static final String TRAINERS_URL = "http://staffservice:8084/trainers";
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    public ResponseEntity<Trainer[]> showTrainers() {
        return restTemplate.exchange(TRAINERS_URL, HttpMethod.GET,
                headersEntity, Trainer[].class);
    }

    public ResponseEntity<Trainer> showTrainerById(UUID id){
        return restTemplate.exchange(TRAINERS_URL + "/" +id, HttpMethod.GET,
                headersEntity, Trainer.class);
    }

    public ResponseEntity<Trainer> addTrainer(TrainerDTO trainerDTO) {
        HttpEntity<TrainerDTO> trainer = new HttpEntity<>(trainerDTO);

        return restTemplate.exchange(TRAINERS_URL, HttpMethod.POST,
                trainer, Trainer.class);
    }

    public ResponseEntity<Void> deleteTrainer(UUID id){
        restTemplate.exchange(TRAINERS_URL + "/"+id, HttpMethod.DELETE,
                headersEntity, Void.class);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Trainer> getClientTrainer(Client client){
        SportsCategory clientSportCategory = client.getSportCategory();

        return restTemplate.exchange(TRAINERS_URL + "/client-category/" + clientSportCategory, HttpMethod.GET,
                headersEntity, Trainer.class);
    }
}
