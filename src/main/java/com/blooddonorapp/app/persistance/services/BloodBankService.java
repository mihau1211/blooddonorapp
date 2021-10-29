package com.blooddonorapp.app.persistance.services;

import com.blooddonorapp.app.exceptions.bloodBank.BloodBankNotFoundException;
import com.blooddonorapp.app.exceptions.donor.DonorNotFoundException;
import com.blooddonorapp.app.models.BloodBank;
import com.blooddonorapp.app.models.Donor;
import com.blooddonorapp.app.persistance.dao.BloodBankRepository;
import com.blooddonorapp.app.persistance.dao.DonorRepository;
import com.blooddonorapp.app.persistance.entities.BloodBankDTO;
import com.blooddonorapp.app.persistance.services.mappers.BloodBankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodBankService {
    private BloodBankMapper mapper;
    private BloodBankRepository repository;
    private DonorRepository donorRepository;

    @Autowired
    public BloodBankService(BloodBankMapper mapper, BloodBankRepository repository, DonorRepository donorRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.donorRepository = donorRepository;
    }

    private BloodBankNotFoundException bloodBankNotFoundException(Long id){
        return new BloodBankNotFoundException(id);
    }

    private DonorNotFoundException donorNotFoundException(Long id){
        return new DonorNotFoundException(id);
    }

    public BloodBankDTO save(final BloodBankDTO bloodBankDTO){
        BloodBank bloodBank = repository.save(mapper.toMap(bloodBankDTO));

        return mapper.toDTO(bloodBank);
    }

    public BloodBankDTO findById(final Long id){
        Optional<BloodBank> optionalBloodBank = repository.findById(id);

        return mapper.toDTO(optionalBloodBank.orElseThrow(() -> bloodBankNotFoundException(id)));
    }

   public List<BloodBankDTO> findAll(){
        return mapper.toList(repository.findAll());
   }

   public void deleteById(final long id){
        try{
            repository.deleteById(id);
        }catch (Exception e){
            throw bloodBankNotFoundException(id);
        }
   }

   public BloodBankDTO update(final BloodBankDTO bloodBankDTO){
        BloodBank bloodBank = repository.findById(bloodBankDTO.getBloodBankId()).orElseThrow(() -> bloodBankNotFoundException(bloodBankDTO.getBloodBankId()));

        return mapper.toDTO(repository.save(mapper.toMap(bloodBankDTO)));
   }

   public BloodBankDTO findByUsername(final String username){
        Optional<BloodBank> optionalBloodBank = repository.findByUsername(username);

        return mapper.toDTO(optionalBloodBank.get());
   }

    public BloodBankDTO findByEmail(final String email){
        Optional<BloodBank> optionalBloodBank = repository.findByEmail(email);

        return mapper.toDTO(optionalBloodBank.get());
    }

    public List<BloodBankDTO> findByCity(final String city){
        return mapper.toList(repository.findByCity(city));
    }

    public BloodBankDTO findByDonorId(final Long id){
        Donor donor = donorRepository.findById(id).orElseThrow(() -> donorNotFoundException(id));
        BloodBank bloodBank = repository.findById(donor.getBloodBank().getBloodBankId()).orElseThrow(() -> bloodBankNotFoundException(donor.getBloodBank().getBloodBankId()));

        return mapper.toDTO(bloodBank);
    }
}
