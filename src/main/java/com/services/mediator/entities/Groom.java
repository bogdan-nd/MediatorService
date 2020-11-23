package com.services.mediator.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Groom {
    private UUID id;
    private String name;
    private int salary;
    private int carePrice;
    private int horseFedNumber;

    public Groom(String name, int salary, int carePrice){
        this.name = name;
        this.salary = salary;
        this.id = UUID.randomUUID();
        this.carePrice = carePrice;
        this.horseFedNumber = 0;
    }

    public void giveFood(){
        horseFedNumber += 1;
    }

}
