package com.services.mediator.services;

import com.services.mediator.entities.Client;
import com.services.mediator.entities.dto.ClientDTO;
import com.services.mediator.exceptions.ClientNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class ClientService {
    private static final String CLIENTS_URL = "http://clientservice:8086/clients";
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    public ResponseEntity<Client[]> showClients() {

        return restTemplate.exchange(CLIENTS_URL, HttpMethod.GET,
                headersEntity, Client[].class);
    }

    public ResponseEntity<Client> getClientById(UUID id) throws ClientNotFoundException {
        if (id == null)
            throw new ClientNotFoundException();

        ResponseEntity<Client> clientResponse = restTemplate.exchange(CLIENTS_URL + "/" + id, HttpMethod.GET,
                headersEntity, Client.class);

        if (clientResponse.getStatusCodeValue() != 200)
            throw new ClientNotFoundException();

        return clientResponse;
    }


    public ResponseEntity<Client> addClient(ClientDTO clientDTO) {
        HttpEntity<ClientDTO> client = new HttpEntity<>(clientDTO);

        return restTemplate.exchange(CLIENTS_URL, HttpMethod.POST,
                client, Client.class);
    }

    public void clientGetMoney(UUID clientId, int price) {
        ResponseEntity<Client> clientResponse = restTemplate.exchange(CLIENTS_URL + String.format("/%s/get/%s", clientId, price),
                HttpMethod.POST, headersEntity, Client.class);
    }

    public void ClientSpendMoney(UUID clientId, int price) throws ClientNotFoundException {
        ResponseEntity<Client> clientResponse = restTemplate.exchange(CLIENTS_URL + String.format("/%s/spend/%s", clientId, price),
                HttpMethod.POST, headersEntity, Client.class);

        if (clientResponse.getStatusCodeValue() != 200)
            throw new ClientNotFoundException();
    }

}
