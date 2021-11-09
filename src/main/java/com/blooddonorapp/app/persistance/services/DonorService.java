package com.blooddonorapp.app.persistance.services;

import com.blooddonorapp.app.exceptions.bloodBank.BloodBankNotFoundException;
import com.blooddonorapp.app.exceptions.donation.DonationNotFoundException;
import com.blooddonorapp.app.exceptions.donor.DonorNotFoundException;
import com.blooddonorapp.app.models.BloodBank;
import com.blooddonorapp.app.models.Donation;
import com.blooddonorapp.app.models.Donor;
import com.blooddonorapp.app.persistance.dao.BloodBankRepository;
import com.blooddonorapp.app.persistance.dao.DonationRepository;
import com.blooddonorapp.app.persistance.dao.DonorRepository;
import com.blooddonorapp.app.persistance.entities.DonationDTO;
import com.blooddonorapp.app.persistance.entities.DonorDTO;
import com.blooddonorapp.app.persistance.services.mappers.DonorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class DonorService {
    private DonorMapper mapper;
    private DonorRepository repository;
    private DonationRepository donationRepository;
    private BloodBankRepository bloodBankRepository;

    @Autowired
    public DonorService(DonorMapper mapper, DonorRepository repository, DonationRepository donationRepository, BloodBankRepository bloodBankRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.donationRepository = donationRepository;
        this.bloodBankRepository = bloodBankRepository;
    }

    private DonationNotFoundException donationNotFoundException(Long id){
        return new DonationNotFoundException(id);
    }

    private DonorNotFoundException donorNotFoundException(Long id){
        return new DonorNotFoundException(id);
    }

    private BloodBankNotFoundException bloodBankNotFoundException(Long id){
        return new BloodBankNotFoundException(id);
    }

    public DonorDTO save(final DonorDTO donorDTO){
        BloodBank bloodBank = bloodBankRepository.findById(donorDTO.getBloodBankId()).orElseThrow(() -> bloodBankNotFoundException(donorDTO.getBloodBankId()));
        donorDTO.set
        Donor donor = repository.save(mapper.toMap(donorDTO, bloodBank));
        donor.setDonations(Collections.emptyList());

        return mapper.toDTO(donor);
    }

    public DonorDTO findById(final Long id){
        Optional<Donor> optionalDonor = repository.findById(id);

        return mapper.toDTO(optionalDonor.orElseThrow(() -> donorNotFoundException(id)));
    }

    public List<DonorDTO> findAll(){
        return mapper.toList(repository.findAll());
    }

    public void deleteById(final Long id){
        try {
            repository.deleteById(id);
        }catch (Exception e){
            throw donorNotFoundException(id);
        }
    }

    public DonorDTO update(final DonorDTO donorDTO){
        BloodBank bloodBank = bloodBankRepository.findById(donorDTO.getBloodBankId()).orElseThrow(() -> bloodBankNotFoundException(donorDTO.getBloodBankId()));
        Donor donor = repository.findById(donorDTO.getDonorId()).orElseThrow(() -> donorNotFoundException(donorDTO.getDonorId()));

        return mapper.toDTO(repository.save(mapper.toMap(donorDTO, bloodBank)));
    }

    public DonorDTO findByUsername(final String username){
        Optional<Donor> optionalDonor = repository.findByUsername(username);
        return mapper.toDTO(optionalDonor.get());
    }

    public DonorDTO findByEmail(final String email){
        Optional<Donor> optionalDonor = repository.findByEmail(email);
        return mapper.toDTO(optionalDonor.get());
    }

    public List<DonorDTO> findBySurname(final String surname){
        return mapper.toList(repository.findBySurname(surname));
    }

    public List<DonorDTO> findByGender(final String gender){
        return mapper.toList(repository.findByGender(gender));
    }

    public List<DonorDTO> findByBloodType(final String bloodType){
        return mapper.toList(repository.findByBloodType(bloodType));
    }

    public List<DonorDTO> findByCity(final String city){
        return mapper.toList(repository.findByCity(city));
    }

    public List<DonorDTO> findByBirthdate(final Date birthdate){
        return mapper.toList(repository.findByBirthdate(birthdate));
    }

    public DonorDTO findDonorByDonationId(final Long id){
        Donation donation = donationRepository.findById(id).orElseThrow(() -> donationNotFoundException(id));
        Donor donor = repository.findById(donation.getDonor().getDonorId()).orElseThrow(() -> donorNotFoundException(donation.getDonor().getDonorId()));

        return mapper.toDTO(donor);
    }
}
