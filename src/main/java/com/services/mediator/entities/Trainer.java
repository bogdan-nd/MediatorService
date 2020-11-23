package com.services.mediator.entities;

import com.services.mediator.enums.SportsCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public final class Trainer {
    private UUID id;
    private String name;
    private int salary;
    private SportsCategory sportCategory;
    private int trainingPrice;

    public Trainer(String name, int salary, SportsCategory sportCategory, int trainingPrice){
        this.name = name;
        this.salary = salary;
        this.id = UUID.randomUUID();
        this.sportCategory = sportCategory;
        this.trainingPrice = trainingPrice;
    }
}
