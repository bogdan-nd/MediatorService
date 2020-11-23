package com.services.mediator.exceptions;

public class HorseNotFoundException extends Exception{
    public HorseNotFoundException(){
        super("Opps, horse does not exist");
    }

    public HorseNotFoundException(String message){
        super(message);
    }
}
