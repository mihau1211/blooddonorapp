package com.blooddonorapp.app.exceptions.donation;

public class DonationNotFoundException extends RuntimeException{
    public DonationNotFoundException(Long id){
        super("Not found Donation with id: "+id);
    }
}
