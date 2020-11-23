package com.services.mediator.controllers;

import com.services.mediator.entities.Club;
import com.services.mediator.entities.Horse;
import com.services.mediator.entities.dto.AppointmentDTO;
import com.services.mediator.entities.dto.PaymentDTO;
import com.services.mediator.exceptions.ClientNotFoundException;
import com.services.mediator.exceptions.ClubNotFoundException;
import com.services.mediator.exceptions.HorseNotFoundException;
import com.services.mediator.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("appointments")
@AllArgsConstructor
public class CareController {
    private final CareService careService;
    private final ClientService clientService;
    private final FinanceService financeService;
    private final GroomService groomService;
    private final VetService vetService;
    private final HorseService horseService;
    private final ClubService clubService;

    @PostMapping("recover")
    public ResponseEntity<String> recoverHorse(@RequestBody AppointmentDTO dto) {
        UUID vetId = dto.getSpecialistId();
        try {
            int vetPrice = vetService.getVetPriceById(vetId);
            PaymentDTO bill = createAppointmentWithBill(dto, vetPrice, "Recover");
            horseService.recoverHorse(dto.getHorseId());
            financeService.createPayment(bill);
            return ResponseEntity.ok("Successfully recover horse");

        } catch (HorseNotFoundException | ClubNotFoundException exception) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(exception.getMessage());
        }
    }

    @PostMapping("feed")
    public ResponseEntity<String> feedHorse(@RequestBody AppointmentDTO dto){
        UUID groomId = dto.getSpecialistId();
        try {
            int groomPrice = groomService.getGroomPriceById(groomId);
            PaymentDTO bill = createAppointmentWithBill(dto, groomPrice, "Feed");
            horseService.feedHorse(dto.getHorseId());
            financeService.createPayment(bill);
            payBill(bill);

            return ResponseEntity.ok("Successfully feed horse");
        } catch (HorseNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Where is the horse? Could not find it :(");
        } catch (ClubNotFoundException exception) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Club does not exist. Money transaction is denied.");
        }
    }

    public PaymentDTO createAppointmentWithBill(AppointmentDTO dto, int price, String description) throws HorseNotFoundException, ClubNotFoundException {
        UUID horseId = dto.getHorseId();
        ResponseEntity<Horse> horseResponse = horseService.getHorseById(horseId);
        Horse horse = horseResponse.getBody();

        ResponseEntity<Club> clubResponse = clubService.getClub();
        Club club = clubResponse.getBody();

        careService.createAppointment(dto);

        assert club != null;
        assert horse != null;
        return financeService.createPaymentDTO(horse, club, price, description);
    }


    private void payBill(PaymentDTO paymentDTO) throws ClubNotFoundException {
        UUID clubId = paymentDTO.getReceiverId();
        UUID senderId = paymentDTO.getSenderId();
        int price = paymentDTO.getMoneyAmount();
        try {
            if (clubId.equals(senderId))
                return;

            clientService.ClientSpendMoney(senderId, price);
            clubService.clubEarnMoney(price);
        } catch (ClientNotFoundException ignored) {

        } catch (ClubNotFoundException e) {
            clientService.clientGetMoney(senderId, price);
            throw e;
        }
    }
}
