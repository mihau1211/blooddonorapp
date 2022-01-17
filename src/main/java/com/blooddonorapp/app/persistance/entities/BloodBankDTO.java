package com.blooddonorapp.app.persistance.entities;

import com.blooddonorapp.app.models.Donation;
import com.blooddonorapp.app.models.Donor;
import lombok.*;

import javax.persistence.Id;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class BloodBankDTO {
    @Id
    private Long bloodBankId;
    private String city;
    private String donationCenter;
    private List<DonationDTO> donations;
    private List<DonorDTO> donors;
}
