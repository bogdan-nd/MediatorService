package com.services.mediator.controllers;

import com.services.mediator.entities.Vet;
import com.services.mediator.entities.dto.VetDTO;
import com.services.mediator.services.VetService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("vets")
@AllArgsConstructor
public class VetController {
    private final VetService vetService;

    @GetMapping
    public ResponseEntity<Vet[]> showVets() {
        return vetService.getVets();
    }

    @GetMapping("{id}")
    public ResponseEntity<Vet> showVetById(@PathVariable UUID id) {
        return vetService.getVetById(id);
    }

    @PostMapping
    public ResponseEntity<Vet> addVet(@RequestBody VetDTO vetDTO) {
        return vetService.addVet(vetDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVet(@PathVariable UUID id){
        return vetService.deleteVet(id);
    }
}