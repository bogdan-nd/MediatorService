package com.services.mediator.services;

import com.services.mediator.entities.Groom;
import com.services.mediator.entities.dto.GroomDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class GroomService {
    private static final String GROOM_URL = "http://staffservice:8084/grooms";
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    public ResponseEntity<Groom[]> showGrooms() {
        return restTemplate.exchange(GROOM_URL , HttpMethod.GET,
                headersEntity, Groom[].class);
    }

    public ResponseEntity<Groom> getGroomById(UUID id){
        return restTemplate.exchange(GROOM_URL + "/"+id, HttpMethod.GET,
                headersEntity,Groom.class);
    }

    public ResponseEntity<Groom> addGroom(GroomDTO groomDTO) {
        HttpEntity<GroomDTO> groom= new HttpEntity<>(groomDTO);

        return restTemplate.exchange(GROOM_URL, HttpMethod.POST,
                groom, Groom.class);
    }

    public ResponseEntity<Void> deleteGroom(UUID id){
        restTemplate.exchange(GROOM_URL + "/"+id, HttpMethod.DELETE,
                headersEntity, Void.class);

        return ResponseEntity.noContent().build();
    }

    public int getGroomPriceById(UUID groomId) throws IllegalArgumentException {
        ResponseEntity<Groom> groomResponse = getGroomById(groomId);
        Groom groom = groomResponse.getBody();
        assert groom != null;

        return groom.getCarePrice();
    }
}
