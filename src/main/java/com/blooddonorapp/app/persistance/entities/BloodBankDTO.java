package com.blooddonorapp.app.persistance.entities;

import com.blooddonorapp.app.models.Donation;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class BloodBankDTO {
    private Long bloodBankId;
    private String username;
    private String email;
    private String password;
    private String city;
    private String donationCenter;
    private List<Donation> donations;

    public BloodBankDTO() {
    }

    public BloodBankDTO(Long bloodBankId, String username, String email, String password, String city, String donationCenter, List<Donation> donations) {
        this.bloodBankId = bloodBankId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.city = city;
        this.donationCenter = donationCenter;
        this.donations = donations;
    }
}
