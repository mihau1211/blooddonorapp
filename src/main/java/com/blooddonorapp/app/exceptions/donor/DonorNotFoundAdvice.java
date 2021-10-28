package com.blooddonorapp.app.exceptions.donor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DonorNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(DonorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String donorNotFoundHandler(DonorNotFoundException exception){
        return exception.getMessage();
    }
}
