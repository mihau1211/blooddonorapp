package com.blooddonorapp.app.persistance.entities;

import lombok.*;

import javax.persistence.Id;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class DonationDTO {
    @Id
    private Long donationId;
    private Long donorId;
    private Long bloodBankId;
    private Date donationDate;
    private String bloodType;
    private int quantity;
}
