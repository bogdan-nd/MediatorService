package com.services.mediator.entities.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PaymentDTO {
    private UUID senderId;
    private UUID receiverId;
    private int moneyAmount;
    private String description;
}
