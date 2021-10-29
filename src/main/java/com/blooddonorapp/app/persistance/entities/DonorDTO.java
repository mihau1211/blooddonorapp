package com.blooddonorapp.app.persistance.entities;

import com.blooddonorapp.app.models.Donation;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class DonorDTO {
    @Id
    private Long donorId;
    private Long bloodBankId;
    private Date birthdate;
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
}
