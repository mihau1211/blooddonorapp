package com.blooddonorapp.app.controllers;

import com.blooddonorapp.app.models.Email;
import com.blooddonorapp.app.persistance.entities.SystemUserDTO;
import com.blooddonorapp.app.persistance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SystemUserDTO> save(@RequestBody SystemUserDTO userDTO){
        return new ResponseEntity<>(service.save(userDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SystemUserDTO> update(@PathVariable Long id, @RequestBody SystemUserDTO userDTO){
        return new ResponseEntity<>(service.update(id, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

    @GetMapping
    public ResponseEntity<List<SystemUserDTO>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemUserDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<SystemUserDTO> findByUsername(@PathVariable String username){
        return new ResponseEntity<>(service.findByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<SystemUserDTO> findByEmail(@PathVariable String email){
        return new ResponseEntity<>(service.findByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/donor/{donorId}")
    public ResponseEntity<SystemUserDTO> findByDonorId(@PathVariable Long donorId){
        return new ResponseEntity<>(service.findByDonorId(donorId), HttpStatus.OK);
    }

    @GetMapping("/bloodBank/{bloodBankId}")
    public ResponseEntity<SystemUserDTO> findByBloodBankId(@PathVariable Long bloodBankId){
        return new ResponseEntity<>(service.findByBloodBankId(bloodBankId), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<SystemUserDTO> login(@RequestBody SystemUserDTO userDTO){
        return new ResponseEntity<>(service.login(userDTO), HttpStatus.OK);
    }

    @PostMapping("/sendmail")
    public void sendMail(@RequestBody Email email) {
        service.sendMail(email);
    }
}
