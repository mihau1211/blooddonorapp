package com.blooddonorapp.app.persistance.entities;

import com.blooddonorapp.app.models.Donation;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DonorDTO {
    private Long donorId;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String gender;
    private String bloodType;
    private String city;
    private int points;
    private List<Donation> donations;

    public DonorDTO() {
    }

    public DonorDTO(Long donorId, String username, String email, String password, String name, String surname, String gender, String bloodType, String city, int points, List<Donation> donations) {
        this.donorId = donorId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.bloodType = bloodType;
        this.city = city;
        this.points = points;
        this.donations = donations;
    }
}
