package com.services.mediator.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Training {
    private UUID id;
    private UUID trainerId;
    private UUID horseId;
    private UUID clientId;
    private LocalDateTime startTime;
    private int duration;

    public Training(UUID trainerId, UUID horseId, UUID clientId, LocalDateTime startTime){
        this.id = UUID.randomUUID();
        this.trainerId = trainerId;
        this.horseId = horseId;
        this.clientId = clientId;
        this.startTime = startTime;
        this.duration = 90;
    }

}
