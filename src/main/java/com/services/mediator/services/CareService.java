package com.services.mediator.services;

import com.services.mediator.entities.Appointment;
import com.services.mediator.entities.dto.AppointmentDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class CareService {
    private static final String CARE_URL = "http://careservice:8087/appointments";
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    public void createAppointmentSend(AppointmentDTO dto){
        HttpEntity<AppointmentDTO> appointment = new HttpEntity<>(dto);
        restTemplate.exchange(CARE_URL, HttpMethod.POST,
                appointment, Appointment.class);
    }

    public ResponseEntity<Appointment[]> getHorsesAppointments(UUID horseId){
        return restTemplate.exchange(CARE_URL +"/horses/"+horseId,
                HttpMethod.GET, headersEntity, Appointment[].class);
    }
}
