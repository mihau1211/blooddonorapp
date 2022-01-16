package com.blooddonorapp.app.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long donationId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="donorId", nullable=false)
    private Donor donor;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="bloodBankId", nullable=false)
    private BloodBank bloodBank;

    public Donation(Long donationId) {
        this.donationId = donationId;
    }

    private Date donationDate;
    private BloodType bloodType;
    private int quantity;
}
