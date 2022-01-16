package com.blooddonorapp.app.exceptions.donor;

public class DonorNotFoundLoginException extends RuntimeException{
    public DonorNotFoundLoginException(String message){
        super(message);
    }
}
