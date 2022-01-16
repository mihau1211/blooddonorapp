package com.blooddonorapp.app.persistance.services.mappers;

import com.blooddonorapp.app.models.BloodBank;
import com.blooddonorapp.app.models.BloodType;
import com.blooddonorapp.app.models.Donation;
import com.blooddonorapp.app.models.Donor;
import com.blooddonorapp.app.persistance.entities.DonationDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DonationMapperImpl {
//    @Override
    public DonationDTO toDto(Donation donation) {
        if(donation == null){
            return null;
        }

        DonationDTO donationDTO = new DonationDTO();

        donationDTO.setDonationId(donation.getDonationId());
        donationDTO.setDonorId(donation.getDonor().getDonorId());
        donationDTO.setBloodBankId(donation.getBloodBank().getBloodBankId());
        donationDTO.setDonationDate(donation.getDonationDate());
        donationDTO.setBloodType(donation.getBloodType().toString());
        donationDTO.setQuantity(donation.getQuantity());

        return donationDTO;
    }

//    @Override
    public Donation toMap(DonationDTO donationDTO) {
        if(donationDTO == null){
            return null;
        }

        Donation donation = new Donation();

        donation.setDonationId(donationDTO.getDonationId());
        donation.setDonor(new Donor(donationDTO.getDonorId()));
        donation.setBloodBank(new BloodBank(donationDTO.getBloodBankId()));
        donation.setDonationDate(donationDTO.getDonationDate());
        donation.setBloodType(BloodType.valueOf(donationDTO.getBloodType()));
        donation.setQuantity(donationDTO.getQuantity());

        return donation;
    }

//    @Override
    public List<DonationDTO> toListDto(List<Donation> list) {
        if(list == null){
            return null;
        }
        List<DonationDTO> listDto = new ArrayList<DonationDTO>(list.size());
        for(Donation donation : list){
            listDto.add(toDto(donation));
        }

        return listDto;
    }

//    @Override
    public List<Donation> toList(List<DonationDTO> listDto) {
        if(listDto == null){
            return null;
        }
        List<Donation> list = new ArrayList<Donation>(listDto.size());
        for(DonationDTO donationDTO : listDto){
            list.add(toMap(donationDTO));
        }

        return list;
    }
}
