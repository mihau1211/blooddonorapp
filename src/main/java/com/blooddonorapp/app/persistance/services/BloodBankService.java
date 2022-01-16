package com.blooddonorapp.app.persistance.services;

import com.blooddonorapp.app.exceptions.bloodBank.BloodBankNotFoundException;
import com.blooddonorapp.app.exceptions.donor.DonorNotFoundException;
import com.blooddonorapp.app.models.BloodBank;
import com.blooddonorapp.app.models.Donor;
import com.blooddonorapp.app.persistance.dao.BloodBankRepository;
import com.blooddonorapp.app.persistance.dao.DonorRepository;
import com.blooddonorapp.app.persistance.entities.BloodBankDTO;
import com.blooddonorapp.app.persistance.services.mappers.*;
//import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodBankService {
    private BloodBankMapperImpl bloodBankMapper;
    private DonationMapperImpl donationMapper;
    private DonorMapperImpl donorMapper;
    private BloodBankRepository repository;
    private DonorRepository donorRepository;

    @Autowired
    public BloodBankService(BloodBankMapperImpl bloodBankMapper, DonationMapperImpl donationMapper, DonorMapperImpl donorMapper, BloodBankRepository repository, DonorRepository donorRepository) {
        this.repository = repository;
        this.bloodBankMapper = bloodBankMapper;
        this.donationMapper = donationMapper;
        this.donorMapper = donorMapper;
        this.donorRepository = donorRepository;
    }

    private BloodBankNotFoundException bloodBankNotFoundException(Long id){
        return new BloodBankNotFoundException(id);
    }

    private DonorNotFoundException donorNotFoundException(Long id){
        return new DonorNotFoundException(id);
    }

    public BloodBankDTO save(final BloodBankDTO bloodBankDTO){
        BloodBank bloodBank = repository.save(bloodBankMapper.toMap(bloodBankDTO));

        return bloodBankMapper.toDto(bloodBank);
    }

    public BloodBankDTO update(final Long id, final BloodBankDTO bloodBankDTO){
        BloodBank bloodBank = repository.findById(id).orElseThrow(() -> bloodBankNotFoundException(id));
        System.out.println(bloodBank);
        bloodBankDTO.setBloodBankId(bloodBank.getBloodBankId());
        if(bloodBankDTO.getUsername() != null){
            bloodBank.setUsername(bloodBankDTO.getUsername());
        }
        if(bloodBankDTO.getEmail() != null){
            bloodBank.setEmail(bloodBankDTO.getEmail());
        }
        if(bloodBankDTO.getPassword() != null){
            bloodBank.setPassword(bloodBankDTO.getPassword());
        }
        if(bloodBankDTO.getCity() != null){
            bloodBank.setCity(bloodBankDTO.getCity());
        }
        if(bloodBankDTO.getDonationCenter() != null){
            bloodBank.setDonationCenter(bloodBankDTO.getDonationCenter());
        }
        if(bloodBankDTO.getDonations() != null){
            bloodBank.setDonations(donationMapper.toList(bloodBankDTO.getDonations()));
        }
        if(bloodBankDTO.getDonors() != null){
            bloodBank.setDonors(donorMapper.toList(bloodBankDTO.getDonors()));
        }
//        System.out.println(bloodBankDTO);

        return bloodBankMapper.toDto(repository.save(bloodBank));
    }

    public BloodBankDTO findById(final Long id){
        Optional<BloodBank> optionalBloodBank = repository.findById(id);

        return bloodBankMapper.toDto(optionalBloodBank.orElseThrow(() -> bloodBankNotFoundException(id)));
    }

   public List<BloodBankDTO> findAll(){
        return bloodBankMapper.toListDto(repository.findAll());
   }

   public void deleteById(final long id){
        try{
            repository.deleteById(id);
        }catch (Exception e){
            throw bloodBankNotFoundException(id);
        }
   }

   public BloodBankDTO update(final BloodBankDTO bloodBankDTO){
        repository.findById(bloodBankDTO.getBloodBankId()).orElseThrow(() -> bloodBankNotFoundException(bloodBankDTO.getBloodBankId()));

        return bloodBankMapper.toDto(repository.save(bloodBankMapper.toMap(bloodBankDTO)));
   }

   public BloodBankDTO findByUsername(final String username){
        Optional<BloodBank> optionalBloodBank = repository.findByUsername(username);

        return bloodBankMapper.toDto(optionalBloodBank.get());
   }

    public BloodBankDTO findByEmail(final String email){
        Optional<BloodBank> optionalBloodBank = repository.findByEmail(email);

        return bloodBankMapper.toDto(optionalBloodBank.get());
    }

    public List<BloodBankDTO> findByCity(final String city){
        return bloodBankMapper.toListDto(repository.findByCity(city));
    }

    public BloodBankDTO findByDonorId(final Long id){
        Donor donor = donorRepository.findById(id).orElseThrow(() -> donorNotFoundException(id));
        BloodBank bloodBank = repository.findById(donor.getBloodBank().getBloodBankId()).orElseThrow(() -> bloodBankNotFoundException(donor.getBloodBank().getBloodBankId()));

        return bloodBankMapper.toDto(bloodBank);
    }
}
