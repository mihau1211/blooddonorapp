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
    @ManyToOne
    @JoinColumn(name="donorId", nullable=false)
    private Donor donor;
    @ManyToOne
    @JoinColumn(name="bloodBankId", nullable=false)
    private BloodBank bloodBank;

    private Date donationDate;
    private BloodType bloodType;
    private int quantity;
}
