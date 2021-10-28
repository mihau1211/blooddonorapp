package com.blooddonorapp.app.exceptions.donor;

public class DonorNotFoundException extends RuntimeException{
    public DonorNotFoundException(Long id){
        super("Not found Donor with id: "+id);
    }
}
