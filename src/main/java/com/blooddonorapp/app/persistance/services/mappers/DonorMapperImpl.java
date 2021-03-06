package com.blooddonorapp.app.persistance.services.mappers;

import com.blooddonorapp.app.models.*;
import com.blooddonorapp.app.persistance.entities.DonationDTO;
import com.blooddonorapp.app.persistance.entities.DonorDTO;
//import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DonorMapperImpl {

    public DonationMapperImpl donationMapper;

    @Autowired
    public DonorMapperImpl(DonationMapperImpl donationMapper){
        this.donationMapper = donationMapper;
    }

    public DonorDTO toDto(Donor donor) {
        if(donor == null){
            return null;
        }

        DonorDTO donorDTO = new DonorDTO();

        donorDTO.setDonorId(donor.getDonorId());
        donorDTO.setBirthdate(donor.getBirthdate());
        donorDTO.setName(donor.getName());
        donorDTO.setSurname(donor.getSurname());
        donorDTO.setGender(donor.getGender().toString());
        donorDTO.setBloodType(donor.getBloodType().toString());
        donorDTO.setCity(donor.getCity());
        donorDTO.setNumberOfDonations(donor.getNumberOfDonations());
        donorDTO.setFirstDonationDate(donor.getFirstDonationDate());
        donorDTO.setLastDonationDate(donor.getLastDonationDate());
        donorDTO.setPoints(donor.getPoints());
        donorDTO.setGoal(donor.getGoal());
        donorDTO.setBloodBankId(donor.getBloodBank().getBloodBankId());
        donorDTO.setDonations(donationMapper.toListDto(donor.getDonations()));
        donorDTO.setTotalDonationAmount(donor.computeTotalDonationAmount());

        return donorDTO;
    }

    public Donor toMap(DonorDTO donorDTO) {
        if (donorDTO == null){
            return null;
        }

        Donor donor = new Donor();

        donor.setDonorId(donorDTO.getDonorId());
        donor.setBirthdate(donorDTO.getBirthdate());
        donor.setName(donorDTO.getName());
        donor.setSurname(donorDTO.getSurname());
        donor.setGender(Gender.valueOf(donorDTO.getGender()));
        donor.setBloodType(BloodType.valueOf(donorDTO.getBloodType()));
        donor.setCity(donorDTO.getCity());
        donor.setNumberOfDonations(donorDTO.getNumberOfDonations());
        donor.setFirstDonationDate(donorDTO.getFirstDonationDate());
        donor.setLastDonationDate(donorDTO.getLastDonationDate());
        donor.setPoints(donorDTO.getPoints());
        donor.setGoal(donorDTO.getGoal());
        donor.setBloodBank(new BloodBank(donorDTO.getBloodBankId()));
        donor.setDonations(donationMapper.toList(donorDTO.getDonations()));

        return donor;
    }

    public List<DonorDTO> toListDto(List<Donor> list) {
        if(list == null){
            return null;
        }
        List<DonorDTO> listDto = new ArrayList<DonorDTO>(list.size());
        for(Donor donor : list){
            listDto.add(toDto(donor));
        }

        return listDto;
    }

    public List<Donor> toList(List<DonorDTO> listDto) {
        if(listDto == null){
            return null;
        }
        List<Donor> list = new ArrayList<Donor>(listDto.size());
        for(DonorDTO donorDTO : listDto){
            list.add(toMap(donorDTO));
        }

        return list;
    }
}
