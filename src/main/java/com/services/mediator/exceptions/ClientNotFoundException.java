package com.services.mediator.exceptions;

public class ClientNotFoundException extends Exception{
    public ClientNotFoundException(){
        super("Opps, I could not find such user");
    }
}
