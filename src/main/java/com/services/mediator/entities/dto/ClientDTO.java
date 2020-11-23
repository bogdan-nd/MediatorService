package com.services.mediator.entities.dto;

import com.services.mediator.enums.HorsemanStatus;
import com.services.mediator.enums.SportsCategory;
import lombok.Data;


@Data
public class ClientDTO {
    private final String name;
    private final SportsCategory sportsCategory;
    private final HorsemanStatus horsemanStatus;

}
