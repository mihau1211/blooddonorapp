package com.blooddonorapp.app.exceptions.user;

public class UserUsernameNotFoundException extends RuntimeException{
    public UserUsernameNotFoundException(String username){
        super("Not found User with username: "+username);
    }
}
