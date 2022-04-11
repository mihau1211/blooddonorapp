package com.blooddonorapp.app.exceptions.user;

public class BloodBankIdNotFoundException extends RuntimeException{
    public BloodBankIdNotFoundException(Long bloodBankId){
        super("Not found User with bloodBankId: "+bloodBankId);
    }
}
