package com.services.mediator.controllers.rest;

import com.services.mediator.entities.*;
import com.services.mediator.entities.dto.ClientDTO;
import com.services.mediator.exceptions.ClientNotFoundException;
import com.services.mediator.services.ClientService;
import com.services.mediator.services.FinanceService;
import com.services.mediator.services.HorseService;
import com.services.mediator.services.TrainerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final TrainerService trainerService;
    private final FinanceService financeService;
    private final HorseService horseService;

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.client.routingKey}")
    private String routingKey;

    @GetMapping("connect")
    public ResponseEntity<String> checkConnection(){
        return ResponseEntity.ok("Connected");
    }

    @GetMapping
    public ResponseEntity<Client[]> showClients() {
        return clientService.showClients();
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> showClientById(@PathVariable UUID id){
        try {
            return clientService.getClientById(id);
        }
        catch (ClientNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping("{id}/choose-trainer")
    public ResponseEntity<Trainer> chooseTrainer(@PathVariable UUID id){
        try {
            ResponseEntity<Client> clientResponse = clientService.getClientById(id);
            Client client = clientResponse.getBody();
            assert client != null;
            return trainerService.getClientTrainer(client);
        }
        catch (ClientNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping("{clientId}/payments")
    public ResponseEntity<FinancePayment[]> getPayments(@PathVariable UUID clientId){
        return financeService.getClientBills(clientId);
    }

    @GetMapping("{clientId}/choose-horse")
    public ResponseEntity<Horse> chooseHorse(@PathVariable UUID clientId){
        try {
            ResponseEntity<Client> clientResponse = clientService.getClientById(clientId);
            Client client = clientResponse.getBody();
            assert client != null;
            return horseService.getSuitableHorse(client);
        }
        catch (ClientNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody ClientDTO clientDTO) {
       return clientService.addClient(clientDTO);
    }

    @PostMapping("rabbitmq")
    public String addClientMQ(@RequestBody ClientDTO clientDTO) {
        rabbitTemplate.convertAndSend(exchange,routingKey,clientDTO);
        return "I've added client successfully";
    }

}
