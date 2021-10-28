package com.blooddonorapp.app.exceptions.bloodBank;

public class BloodBankNotFoundException extends RuntimeException{
    public BloodBankNotFoundException(Long id){
        super("Not found BloodBank with id: "+id);
    }
}
