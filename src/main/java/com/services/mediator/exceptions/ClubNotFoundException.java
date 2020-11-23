package com.services.mediator.exceptions;

public class ClubNotFoundException extends Exception {
    public ClubNotFoundException(){
        super("Club does not exist");
    }

    public ClubNotFoundException(String message){
        super(message);
    }
}
