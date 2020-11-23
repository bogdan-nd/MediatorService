package com.services.mediator.entities;

import com.services.mediator.enums.HorsemanStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Horse {
    private UUID id;
    private String name;
    private UUID ownerId;
    private HorsemanStatus horsemanStatus;
    private boolean isIll;
    private LocalDateTime lastTimeEat;
    private int price;

    public Horse(String name, UUID ownerId, HorsemanStatus horsemanStatus, int price){
        this.id = UUID.randomUUID();
        this.name = name;
        this.ownerId = ownerId;
        this.horsemanStatus = horsemanStatus;
        this.isIll = false;
        this.lastTimeEat = LocalDateTime.now().withNano(0);
        this.price = price;
    }

    public void eat(){
        this.lastTimeEat = LocalDateTime.now().withNano(0);
    }

    public void getRecovered(){
        this.isIll = false;
    }

};
