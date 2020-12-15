package com.services.mediator.controllers.rest;

import com.services.mediator.entities.dto.AppointmentDTO;
import com.services.mediator.entities.dto.PaymentDTO;
import com.services.mediator.services.FinanceService;
import com.services.mediator.services.HorseService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
public class FinanceController {
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.finance.routingKey}")
    private String routingKey;

    @PostMapping("rabbitmq")
    public String createPayment(@RequestBody PaymentDTO dto){
        rabbitTemplate.convertAndSend(exchange,routingKey,dto);
        return "I've added payment successfully";
    }
}
