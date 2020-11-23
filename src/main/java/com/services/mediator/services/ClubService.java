package com.services.mediator.services;

import com.services.mediator.entities.Club;
import com.services.mediator.entities.dto.ClubDTO;
import com.services.mediator.exceptions.ClubNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClubService {
    private static final String CLUB_URL = "http://clubservice:8082/club";
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    public ResponseEntity<Club> getClub() throws ClubNotFoundException {
        ResponseEntity<Club> clubResponse = restTemplate.exchange(CLUB_URL, HttpMethod.GET, headersEntity, Club.class);

        if (clubResponse.getStatusCodeValue() != 200)
            throw new ClubNotFoundException();

        return clubResponse;
    }

    public ResponseEntity<?> addClub(ClubDTO clubAccountDTO) throws ClubNotFoundException {
        HttpEntity<ClubDTO> club = new HttpEntity<>(clubAccountDTO);
        ResponseEntity<Club> clubResponse = restTemplate.exchange(CLUB_URL, HttpMethod.POST,
                club, Club.class);

        if (clubResponse.getStatusCodeValue() != 200)
            throw new ClubNotFoundException();

        return clubResponse;
    }

    public void clubEarnMoney(int price) throws ClubNotFoundException {
        ResponseEntity<String> clubResponse = restTemplate.exchange(CLUB_URL + "/earn/" + price, HttpMethod.POST,
                headersEntity, String.class);

        if (clubResponse.getStatusCodeValue() != 200)
            throw new ClubNotFoundException("Club does not exist. Money transaction is denied.");
    }


}
