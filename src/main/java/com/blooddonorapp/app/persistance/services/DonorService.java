package com.blooddonorapp.app.persistance.services;

import com.blooddonorapp.app.exceptions.bloodBank.BloodBankNotFoundException;
import com.blooddonorapp.app.exceptions.donation.DonationNotFoundException;
import com.blooddonorapp.app.exceptions.donor.DonorNotFoundException;
import com.blooddonorapp.app.models.*;
import com.blooddonorapp.app.persistance.dao.BloodBankRepository;
import com.blooddonorapp.app.persistance.dao.DonationRepository;
import com.blooddonorapp.app.persistance.dao.DonorRepository;
import com.blooddonorapp.app.persistance.entities.BloodBankDTO;
import com.blooddonorapp.app.persistance.entities.DonorDTO;
import com.blooddonorapp.app.persistance.services.mappers.DonorMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DonorService {
    private DonorMapperImpl donorMapper;
    private DonorRepository repository;
    private DonationRepository donationRepository;
    private BloodBankRepository bloodBankRepository;

    @Autowired
    public DonorService(DonorMapperImpl donorMapper, DonorRepository repository, DonationRepository donationRepository, BloodBankRepository bloodBankRepository) {
        this.repository = repository;
        this.donorMapper = donorMapper;
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
        bloodBankRepository.findById(donorDTO.getBloodBankId()).orElseThrow(() -> bloodBankNotFoundException(donorDTO.getBloodBankId()));
        Donor donor = repository.save(donorMapper.toMap(donorDTO));
        donor.setDonations(Collections.emptyList());

        return donorMapper.toDto(donor);
    }

    public DonorDTO findById(final Long id){
        Optional<Donor> optionalDonor = repository.findById(id);

        return donorMapper.toDto(optionalDonor.orElseThrow(() -> donorNotFoundException(id)));
    }

    public List<DonorDTO> findAll(){
        return donorMapper.toListDto(repository.findAll());
    }

    public void deleteById(final Long id){
        try {
            repository.deleteById(id);
        }catch (Exception e){
            throw donorNotFoundException(id);
        }
    }

    public DonorDTO update(final Long donorId, final DonorDTO donorDTO){
        Donor donor = repository.findById(donorId).orElseThrow(() -> donorNotFoundException(donorDTO.getDonorId()));

        if (donorDTO.getBloodBankId() != null) {
            BloodBank bloodBank = bloodBankRepository.findById(donorDTO.getBloodBankId()).orElseThrow(() -> bloodBankNotFoundException(donorDTO.getBloodBankId()));
            donor.setBloodBank(bloodBank);
        }

        if (donorDTO.getDonorId() != null){
            donor.setDonorId(donorDTO.getDonorId());
        }
        if (donorDTO.getBirthdate() != null){
            donor.setBirthdate(donorDTO.getBirthdate());
        }
        if (donorDTO.getName() != null){
            donor.setName(donorDTO.getName());
        }
        if (donorDTO.getSurname() != null){
            donor.setSurname(donorDTO.getSurname());
        }
        if (donorDTO.getGender() != null){
            donor.setGender(Gender.valueOf(donorDTO.getGender()));
        }
        if (donorDTO.getBloodType() != null){
            donor.setBloodType(BloodType.valueOf(donorDTO.getBloodType()));
        }
        if (donorDTO.getCity() != null){
            donor.setCity(donorDTO.getCity());
        }
        if (donorDTO.getNumberOfDonations() != 0){
            donor.setNumberOfDonations(donorDTO.getNumberOfDonations());
        }
        if (donorDTO.getFirstDonationDate() != null){
            donor.setFirstDonationDate(donorDTO.getFirstDonationDate());
        }
        if (donorDTO.getLastDonationDate() != null){
            donor.setLastDonationDate(donorDTO.getLastDonationDate());
        }
        if (donorDTO.getPoints() != 0){
            donor.setPoints(donorDTO.getPoints());
        }

        return donorMapper.toDto(repository.save(donor));
    }

    public List<DonorDTO> findBySurname(final String surname){
        return donorMapper.toListDto(repository.findBySurname(surname));
    }

    public List<DonorDTO> findByGender(final String gender){
        return donorMapper.toListDto(repository.findByGender(Gender.valueOf(gender)));
    }

    public List<DonorDTO> findByBloodType(final String bloodType){
        return donorMapper.toListDto(repository.findByBloodType(BloodType.valueOf(bloodType)));
    }

    public List<DonorDTO> findByCity(final String city){
        return donorMapper.toListDto(repository.findByCity(city));
    }

    public List<DonorDTO> findByBirthdate(final Date birthdate){
        return donorMapper.toListDto(repository.findByBirthdate(birthdate));
    }

    public DonorDTO findDonorByDonationId(final Long id){
        Donation donation = donationRepository.findById(id).orElseThrow(() -> donationNotFoundException(id));
        Donor donor = repository.findById(donation.getDonor().getDonorId()).orElseThrow(() -> donorNotFoundException(donation.getDonor().getDonorId()));

        return donorMapper.toDto(donor);
    }

//    public BloodBank findNearestBloodBank(Double lng, Double lat){
//        List<BloodBank> banks = bloodBankRepository.findAll();
//        Map<BloodBank, Double> distances = new HashMap<>();
//
//        for(BloodBank item : banks){
//            distances.put(item, calcDistance(lng, lat, item));
//        }
//        Collections.sort(distances);
//
//        return distances.get(0);
//    }
//
//    public Double calcDistance(Double lng, Double lat, BloodBank bank){
//        return Math.sqrt((Math.pow(lng-bank.getLongitude(), 2)+Math.pow(lat-bank.getLatitude(), 2)));
//    }
}
