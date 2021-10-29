package com.blooddonorapp.app.persistance.services.mappers;

import com.blooddonorapp.app.models.BloodBank;
import com.blooddonorapp.app.models.BloodType;
import com.blooddonorapp.app.models.Donation;
import com.blooddonorapp.app.models.Donor;
import com.blooddonorapp.app.persistance.entities.DonationDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DonationMapper {
    public Donation toMap(final DonationDTO donationDTO, final Donor donor, final BloodBank bloodBank){
        return Donation.builder()
                .donationId(donationDTO.getDonationId())
                .donor(donor)
                .bloodBank(bloodBank)
                .donationDate(donationDTO.getDonationDate())
                .bloodType(BloodType.valueOf(donationDTO.getBloodType()))
                .quantity(donationDTO.getQuantity())
                .build();
    }

    public DonationDTO toDTO(final Donation donation){
        return DonationDTO.builder()
                .donationId(donation.getDonationId())
                .donorId(donation.getDonor().getDonorId())
                .bloodBankId(donation.getBloodBank().getBloodBankId())
                .donationDate(donation.getDonationDate())
                .quantity(donation.getQuantity())
                .build();
    }

    public List<DonationDTO> toList(final List<Donation> list){
        return list.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
