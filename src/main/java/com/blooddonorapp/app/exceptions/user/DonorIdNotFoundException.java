package com.blooddonorapp.app.exceptions.user;

public class DonorIdNotFoundException extends RuntimeException{
    public DonorIdNotFoundException(Long donorId){
        super("Not found User with donorId: "+donorId);
    }
}
