package com.services.mediator.services;

import com.services.mediator.entities.Club;
import com.services.mediator.entities.FinancePayment;
import com.services.mediator.entities.Horse;
import com.services.mediator.entities.dto.PaymentDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class FinanceService {
    private static final String FINANCE_URL = "http://financeservice:8085/payments";
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    public void createPayment(PaymentDTO paymentDTO) {
        HttpEntity<PaymentDTO> payment = new HttpEntity<>(paymentDTO);
        restTemplate.exchange(FINANCE_URL, HttpMethod.POST,
                payment, FinancePayment.class);
    }

    public ResponseEntity<FinancePayment[]> getClientBills(UUID id) {
        return restTemplate.exchange(FINANCE_URL + "/clients/" + id, HttpMethod.GET,
                headersEntity, FinancePayment[].class);
    }

    public PaymentDTO createPaymentDTO(Horse horse, Club club, int price, String description) {
        UUID clubId = club.getId();
        UUID ownerId = horse.getOwnerId();

        return PaymentDTO.builder()
                .senderId(ownerId)
                .receiverId(clubId)
                .moneyAmount(price)
                .description(description)
                .build();
    }
}
