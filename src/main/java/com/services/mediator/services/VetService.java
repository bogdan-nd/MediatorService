package com.services.mediator.services;

import com.services.mediator.entities.Vet;
import com.services.mediator.entities.dto.VetDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class VetService {
    private static final String VET_URL = "http://staffservice:8084/vets";
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    public ResponseEntity<Vet[]> getVets() {
        return restTemplate.exchange(VET_URL, HttpMethod.GET,
                headersEntity, Vet[].class);
    }

    public ResponseEntity<Vet> getVetById(UUID id) {
        return restTemplate.exchange(VET_URL+"/"+id, HttpMethod.GET,
                headersEntity, Vet.class);
    }

    public ResponseEntity<Vet> addVet(VetDTO vetDTO) {
        HttpEntity<VetDTO> vet = new HttpEntity<>(vetDTO);
        return restTemplate.exchange(VET_URL, HttpMethod.POST,
                vet, Vet.class);
    }

    public ResponseEntity<Void> deleteVet(UUID id){
        restTemplate.exchange(VET_URL + "/"+id, HttpMethod.DELETE,
                headersEntity, Void.class);
        return ResponseEntity.noContent().build();
    }

    public int getVetPriceById(UUID vetId){
        ResponseEntity<Vet> vetResponse = getVetById(vetId);
        Vet vet = vetResponse.getBody();
        assert vet != null;
        return vet.getConsultationPrice();
    }
}
