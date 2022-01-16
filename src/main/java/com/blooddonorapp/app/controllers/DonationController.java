package com.blooddonorapp.app.controllers;

import com.blooddonorapp.app.persistance.entities.DonationDTO;
import com.blooddonorapp.app.persistance.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/donation")
public class DonationController {
    private DonationService service;

    @Autowired
    public DonationController(DonationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DonationDTO> save(@RequestBody DonationDTO donationDTO){
        return new ResponseEntity<>(service.save(donationDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DonationDTO> update(@PathVariable Long id, @RequestBody DonationDTO donationDTO){
        return new ResponseEntity<>(service.update(id, donationDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

    @GetMapping
    public ResponseEntity<List<DonationDTO>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DonationDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/donor/{id}")
    public ResponseEntity<List<DonationDTO>> findByDonor(@PathVariable Long id){
        return new ResponseEntity<>(service.findByDonorId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/bloodBank/{id}")
    public ResponseEntity<List<DonationDTO>> findByBloodBank(@PathVariable Long id){
        return new ResponseEntity<>(service.findByBloodBankId(id), HttpStatus.OK);
    }

    @GetMapping(value = "bloodType/{id}")
    public ResponseEntity<List<DonationDTO>> findByBloodType(@PathVariable String bloodType){
        return new ResponseEntity<>(service.findByBloodType(bloodType), HttpStatus.OK);
    }

    @GetMapping(value = "/date/{date}")
    public ResponseEntity<List<DonationDTO>> findByDonationDate(@PathVariable Date date){
        return new ResponseEntity<>(service.findByDonationDate(date), HttpStatus.OK);
    }
}