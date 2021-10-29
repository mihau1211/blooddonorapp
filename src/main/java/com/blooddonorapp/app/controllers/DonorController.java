package com.blooddonorapp.app.controllers;

import com.blooddonorapp.app.persistance.entities.DonorDTO;
import com.blooddonorapp.app.persistance.services.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @GetMapping("/username/{username}")
    public ResponseEntity<DonorDTO> findByUsername(@PathVariable String username){
        return new ResponseEntity<>(service.findByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<DonorDTO> findByEmail(@PathVariable String email){
        return new ResponseEntity<>(service.findByEmail(email), HttpStatus.OK);
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
    public ResponseEntity<List<DonorDTO>> findByBirthdate(@PathVariable Date birthdate){
        return new ResponseEntity<>(service.findByBirthdate(birthdate), HttpStatus.OK);
    }

    @GetMapping("/donation/{id}")
    public ResponseEntity<DonorDTO> findByDonationId(@PathVariable Long id){
        return new ResponseEntity<>(service.findDonorByDonationId(id), HttpStatus.OK);
    }
}
