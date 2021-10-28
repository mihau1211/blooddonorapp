package com.blooddonorapp.app.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class BloodBank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bloodBankId;
    private String username;
    private String email;
    private String password;
    private String city;
    private String donationCenter;

    @OneToMany(mappedBy="bloodBank")
    private List<Donation> donations;
}
