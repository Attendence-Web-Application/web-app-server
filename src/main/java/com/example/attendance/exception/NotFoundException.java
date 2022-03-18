package com.example.attendance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(){
        super("Queried id is not in the DB");
        System.out.println("NotFoundException: "+ "Queried id is not in the DB");
    }
    public NotFoundException(String message){
        super(message);
        System.out.println("NotFoundException: "+message);
    }
}
