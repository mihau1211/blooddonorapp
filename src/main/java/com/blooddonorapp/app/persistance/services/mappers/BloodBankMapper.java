package com.blooddonorapp.app.persistance.services.mappers;


import com.blooddonorapp.app.models.BloodBank;
import com.blooddonorapp.app.persistance.entities.BloodBankDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BloodBankMapper {
    public BloodBank toMap(final BloodBankDTO bloodBankDTO){
        return BloodBank.builder()
                .bloodBankId(bloodBankDTO.getBloodBankId())
                .username(bloodBankDTO.getUsername())
                .email(bloodBankDTO.getEmail())
                .password(bloodBankDTO.getPassword())
                .city(bloodBankDTO.getCity())
                .donationCenter(bloodBankDTO.getDonationCenter())
                .donations(bloodBankDTO.getDonations())
                .build();
    }

    public BloodBankDTO toDTO(final BloodBank bloodBank){
        return BloodBankDTO.builder()
                .bloodBankId(bloodBank.getBloodBankId())
                .username(bloodBank.getUsername())
                .email(bloodBank.getEmail())
                .password(bloodBank.getPassword())
                .city(bloodBank.getCity())
                .donationCenter(bloodBank.getDonationCenter())
                .donations(bloodBank.getDonations())
                .build();
    }

    public List<BloodBankDTO> toList(final List<BloodBank> list){
        return list.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}