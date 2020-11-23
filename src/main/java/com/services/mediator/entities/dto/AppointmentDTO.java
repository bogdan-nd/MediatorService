package com.services.mediator.entities.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AppointmentDTO {
    private UUID horseId;
    private UUID specialistId;
}
