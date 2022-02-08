package com.blooddonorapp.app.controllers;

import com.blooddonorapp.app.persistance.entities.DonorDTO;
import com.blooddonorapp.app.persistance.services.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/donor")
public class DonorController {
    private DonorService service;

    @Autowired
    public DonorController(DonorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DonorDTO> save(@RequestBody DonorDTO donorDTO){
        return new ResponseEntity<>(service.save(donorDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DonorDTO> update(@PathVariable Long id, @RequestBody DonorDTO donorDTO){
        return new ResponseEntity<>(service.update(id, donorDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

    @GetMapping
    public ResponseEntity<List<DonorDTO>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonorDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/surname/{surname}")
    public ResponseEntity<List<DonorDTO>> findBySurname(@PathVariable String surname){
        return new ResponseEntity<>(service.findBySurname(surname), HttpStatus.OK);
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<DonorDTO>> findByGender(@PathVariable String gender){
        return new ResponseEntity<>(service.findByGender(gender), HttpStatus.OK);
    }

    @GetMapping("/bloodType/{bloodType}")
    public ResponseEntity<List<DonorDTO>> findByBloodType(@PathVariable String bloodType){
        return new ResponseEntity<>(service.findByBloodType(bloodType), HttpStatus.OK);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<DonorDTO>> findByCity(@PathVariable String city){
        return new ResponseEntity<>(service.findByCity(city), HttpStatus.OK);
    }

    @GetMapping("/birthdate/{date}")
    public ResponseEntity<List<DonorDTO>> findByBirthdate(@PathVariable("date") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date birthdate){
        return new ResponseEntity<>(service.findByBirthdate(birthdate), HttpStatus.OK);
    }

    @GetMapping("/donation/{id}")
    public ResponseEntity<DonorDTO> findByDonationId(@PathVariable Long id){
        return new ResponseEntity<>(service.findDonorByDonationId(id), HttpStatus.OK);
    }
}
