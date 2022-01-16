package com.blooddonorapp.app.exceptions.user;

public class UserEmailNotFoundException extends RuntimeException{
    public UserEmailNotFoundException(String email){
        super("Not found User with email: "+email);
    }
}
