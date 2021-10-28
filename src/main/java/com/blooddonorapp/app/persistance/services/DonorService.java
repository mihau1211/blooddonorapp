package com.blooddonorapp.app.persistance.services;

import com.blooddonorapp.app.exceptions.donation.DonationNotFoundException;
import com.blooddonorapp.app.exceptions.donor.DonorNotFoundException;
import com.blooddonorapp.app.models.Donor;
import com.blooddonorapp.app.persistance.dao.DonationRepository;
import com.blooddonorapp.app.persistance.dao.DonorRepository;
import com.blooddonorapp.app.persistance.entities.DonorDTO;
import com.blooddonorapp.app.persistance.services.mappers.DonorMapperInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorService {
    private DonorMapperInterface mapper;
    private DonorRepository repository;
    private DonationRepository donationRepository;

    @Autowired
    public DonorService(DonorMapperInterface mapper, DonorRepository repository, DonationRepository donationRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.donationRepository = donationRepository;
    }

    private DonationNotFoundException donationNotFoundException(Long id){
        return new DonationNotFoundException(id);
    }

    private DonorNotFoundException donorNotFoundException(Long id){
        return new DonorNotFoundException(id);
    }

    public DonorDTO save(final DonorDTO donorDTO){
        Donor donor = repository.save(mapper.toMap(donorDTO));

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
        Donor donor = repository.findById(donorDTO.getDonorId()).orElseThrow(() -> donorNotFoundException(donorDTO.getDonorId()));

        return mapper.toDTO(repository.save(mapper.toMap(donorDTO)));
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

    public DonorDTO findDonorByDonationId(final Long id){
        donationRepository.findById(id).orElseThrow(() -> donationNotFoundException(id));

        Optional<Donor> optionalDonor = repository.findDonorByDonationId(id);

        return mapper.toDTO(optionalDonor.get());
    }
}
