package com.blooddonorapp.app.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long donorId;
    private Date birthdate;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Gender gender;
    private BloodType bloodType;
    private String city;
    private int numberOfDonations;
    private Date firstDonationDate;
    private Date lastDonationDate;
    private int points;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bloodBankId")
    private BloodBank bloodBank;

    @OneToMany(mappedBy="donor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Donation> donations;

    public void addDonation(Donation donation){
        this.donations.add(donation);
    }
}
