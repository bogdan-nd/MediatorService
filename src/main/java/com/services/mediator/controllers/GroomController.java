package com.services.mediator.controllers;

import com.services.mediator.entities.Groom;
import com.services.mediator.entities.dto.GroomDTO;
import com.services.mediator.services.GroomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("grooms")
@AllArgsConstructor
public class GroomController {
    private final GroomService groomService;

    @GetMapping
    public ResponseEntity<Groom[]> showGrooms() {
        return groomService.showGrooms();
    }

    @GetMapping("{id}")
    public ResponseEntity<Groom> showGroomById(@PathVariable UUID id){
        return groomService.getGroomById(id);
    }

    @PostMapping
    public ResponseEntity<Groom> addGroom(@RequestBody GroomDTO groomDTO) {
        return groomService.addGroom(groomDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGroom(@PathVariable UUID id){
        return groomService.deleteGroom(id);
    }
}
