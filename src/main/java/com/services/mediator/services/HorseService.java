package com.services.mediator.services;

import com.services.mediator.entities.Client;
import com.services.mediator.entities.Horse;
import com.services.mediator.entities.dto.HorseDTO;
import com.services.mediator.entities.enums.HorsemanStatus;
import com.services.mediator.exceptions.HorseNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class HorseService {
    private static final String HORSE_URL = "http://horseservice:8083/horses";
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    public ResponseEntity<Horse[]> getHorses() {
        return restTemplate.exchange(HORSE_URL , HttpMethod.GET,
                headersEntity, Horse[].class);
    }

    public ResponseEntity<Horse> getHorseById(UUID id) throws HorseNotFoundException {
        ResponseEntity<Horse> horseResponse =  restTemplate.exchange(HORSE_URL + "/" + id, HttpMethod.GET,
                headersEntity, Horse.class);

        if(horseResponse.getStatusCodeValue() != 200)
            throw new HorseNotFoundException();

        return horseResponse;
    }

    public ResponseEntity<Horse[]> getIllHorses() {
        return restTemplate.exchange(HORSE_URL + "/ill", HttpMethod.GET,
                headersEntity, Horse[].class);
    }

    public ResponseEntity<Horse> addHorse(HorseDTO horseDTO) {
        HttpEntity<HorseDTO> horse = new HttpEntity<>(horseDTO);
        return restTemplate.exchange(HORSE_URL , HttpMethod.POST,
                horse, Horse.class);
    }

    public ResponseEntity<String> feedHorse(UUID horseId) {
        return restTemplate.exchange(HORSE_URL + "/" + horseId + "/eat", HttpMethod.POST,
                headersEntity, String.class);
    }

    public ResponseEntity<String> recoverHorse(UUID horseId) {
        return restTemplate.exchange(HORSE_URL + "/" + horseId + "/recover", HttpMethod.POST,
                headersEntity, String.class);
    }

    public ResponseEntity<Void> deleteHorse(UUID id) {
        HttpEntity<UUID> deletedHorseId = new HttpEntity<>(id);
        ResponseEntity<Horse> response = restTemplate.exchange(HORSE_URL + "/" + id, HttpMethod.DELETE,
                deletedHorseId, Horse.class);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Horse> getSuitableHorse(Client client){
        HorsemanStatus clientStatus = client.getHorsemanStatus();

        return restTemplate.exchange(HORSE_URL +"/status/" + clientStatus , HttpMethod.GET,
                headersEntity, Horse.class);
    }


}
