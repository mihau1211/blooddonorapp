package com.blooddonorapp.app.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BloodBank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bloodBankId;
    private String city;
    private String donationCenter;

    public BloodBank(Long bloodBankId) {
        this.bloodBankId = bloodBankId;
    }

    @OneToMany(mappedBy="bloodBank", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<Donation> donations;

    @OneToMany(mappedBy = "bloodBank", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<Donor> donors;
}
