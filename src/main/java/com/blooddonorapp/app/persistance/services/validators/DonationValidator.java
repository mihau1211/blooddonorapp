package com.blooddonorapp.app.persistance.services.validators;

import com.blooddonorapp.app.models.Donation;
import com.blooddonorapp.app.persistance.entities.DonationDTO;
import org.springframework.stereotype.Component;

@Component
public class DonationValidator {
    public void donationUpdateValidator(DonationDTO donationDTO, Donation donation){
        if (donationDTO.getDonorId() == null){
            donationDTO.setDonorId(donation.getDonationId());
        }
        if (donationDTO.getBloodBankId() == null){
            donationDTO.setBloodBankId(donation.getBloodBank().getBloodBankId());
        }
//        if (donationDTO.getStatus() == null){
//            donationDTO.setStatus(donation.getStatus());
//        }
        if (donationDTO.getDonationDate() == null){
            donationDTO.setDonationDate(donation.getDonationDate());
        }
        if (donationDTO.getBloodType() == null){
            donationDTO.setBloodType(donation.getBloodType().toString());
        }
        if (donationDTO.getQuantity() == 0){
            donationDTO.setQuantity(donation.getQuantity());
        }
    }
}
