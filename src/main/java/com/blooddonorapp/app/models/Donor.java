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
    private String name;
    private String surname;
    private Gender gender;
    private BloodType bloodType;
    private String city;
    private int numberOfDonations;
    private Date firstDonationDate;
    private Date lastDonationDate;
    private int points;

    public Donor(Long donorId) {
        this.donorId = donorId;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "bloodBankId")
    private BloodBank bloodBank;

    @OneToMany(mappedBy="donor", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Donation> donations;

    public int computeTotalDonationAmount(){
        int totalDonationAmount = 0;
        for(int i=0; i< donations.size(); i++){
            totalDonationAmount += donations.get(i).getQuantity();
        }
        return totalDonationAmount;
    }
}
