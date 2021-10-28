package com.blooddonorapp.app.persistance.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class DonationDTO {
    private Long donationId;
    private Long donorId;
    private Long bloodBankId;
    private Date donationDate;
    private String bloodType;
    private int quantity;

    public DonationDTO() {
    }

    public DonationDTO(Long donationId, Long donorId, Long bloodBankId, Date donationDate, String bloodType, int quantity) {
        this.donationId = donationId;
        this.donorId = donorId;
        this.bloodBankId = bloodBankId;
        this.donationDate = donationDate;
        this.bloodType = bloodType;
        this.quantity = quantity;
    }
}
