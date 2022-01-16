package com.blooddonorapp.app.persistance.services.mappers;

import com.blooddonorapp.app.models.BloodBank;
import com.blooddonorapp.app.persistance.entities.BloodBankDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BloodBankMapperImpl{

    public DonationMapperImpl donationMapper;
    public DonorMapperImpl donorMapper;

    @Autowired
    public BloodBankMapperImpl(DonationMapperImpl donationMapper, DonorMapperImpl donorMapper){
        this.donationMapper = donationMapper;
        this.donorMapper = donorMapper;
    }


    public BloodBank toMap(BloodBankDTO bloodBankDTO) {
        if(bloodBankDTO == null){
            return null;
        }

        BloodBank bloodBank = new BloodBank();

        bloodBank.setBloodBankId(bloodBankDTO.getBloodBankId());
        bloodBank.setCity(bloodBankDTO.getCity());
        bloodBank.setDonationCenter(bloodBankDTO.getDonationCenter());
        bloodBank.setDonations(donationMapper.toList(bloodBankDTO.getDonations()));
        bloodBank.setDonors(donorMapper.toList(bloodBankDTO.getDonors()));

        return bloodBank;
    }


    public BloodBankDTO toDto(BloodBank bloodBank) {
        if(bloodBank == null){
            return null;
        }

        BloodBankDTO bloodBankDTO = new BloodBankDTO();

        bloodBankDTO.setBloodBankId(bloodBank.getBloodBankId());
        bloodBankDTO.setCity(bloodBank.getCity());
        bloodBankDTO.setDonationCenter(bloodBank.getDonationCenter());
        bloodBankDTO.setDonations(donationMapper.toListDto(bloodBank.getDonations()));
        bloodBankDTO.setDonors(donorMapper.toListDto(bloodBank.getDonors()));

        return bloodBankDTO;
    }


    public List<BloodBankDTO> toListDto(List<BloodBank> list) {
        if(list == null){
            return null;
        }
        List<BloodBankDTO> listDto = new ArrayList<BloodBankDTO>(list.size());
        for(BloodBank bloodBank : list){
            listDto.add(toDto(bloodBank));
        }

        return listDto;
    }


    public List<BloodBank> toList(List<BloodBankDTO> listDto) {
        if(listDto == null){
            return null;
        }
        List<BloodBank> list = new ArrayList<BloodBank>(listDto.size());
        for(BloodBankDTO bloodBankDTO : listDto){
            list.add(toMap(bloodBankDTO));
        }

        return list;
    }
}
