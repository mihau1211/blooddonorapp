package com.blooddonorapp.app.controllers;


import com.blooddonorapp.app.persistance.entities.BloodBankDTO;
import com.blooddonorapp.app.persistance.services.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bloodBank")
public class BloodBankController {
    BloodBankService service;

    @Autowired
    public BloodBankController(BloodBankService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BloodBankDTO> save(@RequestBody BloodBankDTO bloodBankDTO) {
        return new ResponseEntity<>(service.save(bloodBankDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BloodBankDTO> update(@PathVariable Long id, @RequestBody BloodBankDTO bloodBankDTO){
        return new ResponseEntity<>(service.update(id, bloodBankDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping
    public ResponseEntity<List<BloodBankDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodBankDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<BloodBankDTO>> findByCity(@PathVariable String city) {
        return new ResponseEntity<>(service.findByCity(city), HttpStatus.OK);

    }

    @GetMapping("/donor/{id}")
    public ResponseEntity<BloodBankDTO> findByUsername(@PathVariable Long id){
        return new ResponseEntity<>(service.findByDonorId(id), HttpStatus.OK);

    }
}