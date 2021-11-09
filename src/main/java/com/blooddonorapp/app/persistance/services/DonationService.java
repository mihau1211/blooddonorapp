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
import com.blooddonorapp.app.persistance.services.mappers.DonationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DonationService {
    private DonationMapper mapper;
    private DonationRepository repository;
    private DonorRepository donorRepository;
    private BloodBankRepository bloodBankRepository;

    @Autowired
    public DonationService(DonationMapper mapper, DonationRepository repository, DonorRepository donorRepository, BloodBankRepository bloodBankRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.donorRepository = donorRepository;
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

    public DonationDTO save(final DonationDTO donationDTO){
        Donor donor = donorRepository.findById(donationDTO.getDonorId()).orElseThrow(() -> donorNotFoundException(donationDTO.getDonorId()));
        BloodBank bloodBank = bloodBankRepository.findById(donationDTO.getBloodBankId()).orElseThrow(() -> bloodBankNotFoundException(donationDTO.getBloodBankId()));
        System.out.println(donationDTO.getQuantity());

        Donation donation = repository.save(mapper.toMap(donationDTO, donor, bloodBank));
        return mapper.toDTO(donation);
    }

    public List<DonationDTO> findAll(){
        return mapper.toList(repository.findAll());
    }

    public DonationDTO findById(final long id){
        Optional<Donation> optionalDonation = repository.findById(id);

        return mapper.toDTO(optionalDonation.orElseThrow(() -> donationNotFoundException(id)));
    }

    public void deleteById(final Long id){
        try{
            repository.deleteById(id);
        }catch (Exception e){
            throw donationNotFoundException(id);
        }
    }

    public DonationDTO update(final Long id, final DonationDTO donationDTO){
        Donation donation = repository.findById(id).orElseThrow(() -> donationNotFoundException(donationDTO.getDonationId()));
        Donor donor = donorRepository.findById(donationDTO.getDonorId()).orElseThrow(() -> donorNotFoundException(donationDTO.getDonorId()));
        BloodBank bloodBank = bloodBankRepository.findById(donationDTO.getBloodBankId()).orElseThrow(() -> bloodBankNotFoundException(donationDTO.getBloodBankId()));

        donationDTO.setDonationId(donation.getDonationId());
        if (donationDTO.getDonorId() == null){
            donationDTO.setDonorId(donation.getDonationId());
        }
        if (donationDTO.getBloodBankId() == null){
            donationDTO.setBloodBankId(donation.getBloodBank().getBloodBankId());
        }
//        if (donationDTO.getStatus() == null){
//            donationDTO.setStatus(donation.getStatus());
//        }
        if (donationDTO.getDonationDate() == null){
            donationDTO.setDonationDate(donation.getDonationDate());
        }
        if (donationDTO.getBloodType() == null){
            donationDTO.setBloodType(donation.getBloodType().toString());
        }
        if (donationDTO.getQuantity() == 0){
            donationDTO.setQuantity(donation.getQuantity());
        }

        return mapper.toDTO(repository.save(mapper.toMap(donationDTO, donor, bloodBank)));
    }

    public List<DonationDTO> findByDonorId(final Long id){
        Donor donor = donorRepository.findById(id).orElseThrow(() -> donorNotFoundException(id));

        return mapper.toList(repository.findByDonor(donor));
    }

    public List<DonationDTO> findByBloodBankId(final Long id){
        BloodBank bloodBank = bloodBankRepository.findById(id).orElseThrow(() -> bloodBankNotFoundException(id));

        return mapper.toList(repository.findByBloodBank(bloodBank));
    }

    public List<DonationDTO> findByBloodType(final String bloodType){
        return mapper.toList(repository.findByBloodType(bloodType));
    }

    public List<DonationDTO> findByDonationDate(final Date date) {
        return mapper.toList(repository.findByDonationDate(date));
    }
}