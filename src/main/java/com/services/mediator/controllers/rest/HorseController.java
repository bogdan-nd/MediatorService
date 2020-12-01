package com.services.mediator.controllers.rest;

import com.services.mediator.entities.Appointment;
import com.services.mediator.entities.Club;
import com.services.mediator.entities.Horse;
import com.services.mediator.entities.dto.HorseDTO;
import com.services.mediator.exceptions.ClientNotFoundException;
import com.services.mediator.exceptions.ClubNotFoundException;
import com.services.mediator.exceptions.HorseNotFoundException;
import com.services.mediator.services.CareService;
import com.services.mediator.services.ClientService;
import com.services.mediator.services.ClubService;
import com.services.mediator.services.HorseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("horses")
@AllArgsConstructor
public class HorseController {
    private final ClubService clubService;
    private final HorseService horseService;
    private final ClientService clientService;
    private final CareService careService;

    @GetMapping
    public ResponseEntity<Horse[]> getHorses() {
        return horseService.getHorses();
    }

    @GetMapping("{id}")
    public ResponseEntity<Horse> getHorseById(@PathVariable UUID id) {
        try {
            return horseService.getHorseById(id);
        } catch (HorseNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping("ill")
    public ResponseEntity<Horse[]> getIllHorses() {
        return horseService.getIllHorses();
    }

    @PostMapping
    public ResponseEntity<?> addHorse(@RequestBody HorseDTO horseDTO) {
        UUID ownerId = horseDTO.getOwnerId();
        try {
            clientService.getClientById(ownerId);
            return ResponseEntity.ok(Objects.requireNonNull(horseService.addHorse(horseDTO).getBody()));

        } catch (ClientNotFoundException e) {
            try {
                ResponseEntity<Club> clubResponse = clubService.getClub();
                UUID clubId = Objects.requireNonNull(clubResponse.getBody()).getId();
                horseDTO.setOwnerId(clubId);
                return horseService.addHorse(horseDTO);
            } catch (ClubNotFoundException exp) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("It is a free horse. It does not have an owner!");
            }
        }
    }

    @GetMapping("{horseId}/appointments")
    public ResponseEntity<Appointment[]> getHorseAppointments(@PathVariable UUID horseId) {
        return careService.getHorsesAppointments(horseId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteHorse(@PathVariable UUID id) {
        return horseService.deleteHorse(id);
    }
}
