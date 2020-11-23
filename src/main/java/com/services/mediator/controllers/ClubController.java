package com.services.mediator.controllers;

import com.services.mediator.entities.Club;
import com.services.mediator.entities.dto.ClubDTO;
import com.services.mediator.exceptions.ClubNotFoundException;
import com.services.mediator.services.ClubService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping("club")
@AllArgsConstructor
public class ClubController {
    private final ClubService clubService;

    @GetMapping
    public ResponseEntity<Club> getClub(){
        try {
            return clubService.getClub();
        }
        catch (ClubNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addClub(@RequestBody ClubDTO clubAccountDTO){
        try {
            return clubService.addClub(clubAccountDTO);
        }
        catch (ClubNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

}
