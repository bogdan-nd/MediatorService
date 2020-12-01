package com.services.mediator.controllers.rest;


import com.services.mediator.entities.Trainer;
import com.services.mediator.entities.dto.TrainerDTO;
import com.services.mediator.services.TrainerService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("trainers")
@AllArgsConstructor
public class TrainerController {
    private final TrainerService trainerService;

    @GetMapping
    public ResponseEntity<Trainer[]> showTrainers() {
        return trainerService.showTrainers();
    }

    @GetMapping("{id}")
    public ResponseEntity<Trainer> showTrainerById(@PathVariable UUID id){
        return trainerService.showTrainerById(id);
    }


    @PostMapping
    public ResponseEntity<Trainer> addTrainer(@RequestBody TrainerDTO trainerDTO) {
        return trainerService.addTrainer(trainerDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable UUID id){
        return trainerService.deleteTrainer(id);
    }
}
