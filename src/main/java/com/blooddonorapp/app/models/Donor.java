package com.blooddonorapp.app.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Donor")
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long donorId;
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

    @OneToMany(mappedBy="donor")
    private List<Donation> donations;

    public Donor() {
    }

    public Donor(Long donorId, String username, String email, String password, String name, String surname, Gender gender, BloodType bloodType, String city, int numberOfDonations, Date firstDonationDate, Date lastDonationDate, int points, List<Donation> donations) {
        this.donorId = donorId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.bloodType = bloodType;
        this.city = city;
        this.numberOfDonations = numberOfDonations;
        this.firstDonationDate = firstDonationDate;
        this.lastDonationDate = lastDonationDate;
        this.points = points;
        this.donations = donations;
    }
}
