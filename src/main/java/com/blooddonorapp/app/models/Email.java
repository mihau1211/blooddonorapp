package com.blooddonorapp.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Email {
    private String to;
    private String from;
    private String subject;
    private String message;
    private String password;
}
