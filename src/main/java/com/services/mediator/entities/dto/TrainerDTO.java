package com.services.mediator.entities.dto;

import com.services.mediator.enums.SportsCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrainerDTO {
    private String name;
    private int salary;
    private SportsCategory sportCategory;
    private int trainingPrice;
}
