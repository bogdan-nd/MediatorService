package com.services.mediator.entities;


import com.services.mediator.enums.HorsemanStatus;
import com.services.mediator.enums.SportsCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private UUID id;
    private String name;
    private int creditMoney;
    private SportsCategory sportCategory;
    private HorsemanStatus horsemanStatus;

    public Client(String name, SportsCategory sportCategory, HorsemanStatus horsemanStatus){
        this.id = UUID.randomUUID();
        this.name = name;
        this.creditMoney = 0;
        this.sportCategory = sportCategory;
        this.horsemanStatus = horsemanStatus;
    }

    public void spendMoney(int moneyAmount){
        creditMoney += moneyAmount;
    }

    public boolean sameClient(Client secondClient){
        return id.equals(secondClient.id);
    }
}
