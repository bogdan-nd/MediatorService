package com.services.mediator.entities.dto;

import com.services.mediator.enums.HorsemanStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class HorseDTO {
    private final String name;
    private UUID ownerId;
    private final HorsemanStatus horsemanStatus;
    private final int price;
}
