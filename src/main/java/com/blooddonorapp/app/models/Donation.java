package com.blooddonorapp.app.models;

import lombok.Builder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long donationId;
    @ManyToOne
    @JoinColumn(name="donorId", nullable=false)
    private Donor donor;
    @ManyToOne
    @JoinColumn(name="bloodBankId", nullable=false)
    private BloodBank bloodBank;

    private Date donationDate;
    private BloodType bloodType;
    private int quantity;

    public Donation() {
    }

    public Donation(Long donationId, Donor donor, BloodBank bloodBank, Date donationDate, BloodType bloodType, int quantity) {
        this.donationId = donationId;
        this.donor = donor;
        this.bloodBank = bloodBank;
        this.donationDate = donationDate;
        this.bloodType = bloodType;
        this.quantity = quantity;
    }
}
