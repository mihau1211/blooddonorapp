package com.blooddonorapp.app.persistance.services;

import com.blooddonorapp.app.exceptions.bloodBank.BloodBankNotFoundException;
import com.blooddonorapp.app.exceptions.donation.DonationNotFoundException;
import com.blooddonorapp.app.exceptions.donor.DonorNotFoundException;
import com.blooddonorapp.app.models.BloodBank;
import com.blooddonorapp.app.models.BloodType;
import com.blooddonorapp.app.models.Donation;
import com.blooddonorapp.app.models.Donor;
import com.blooddonorapp.app.persistance.dao.BloodBankRepository;
import com.blooddonorapp.app.persistance.dao.DonationRepository;
import com.blooddonorapp.app.persistance.dao.DonorRepository;
import com.blooddonorapp.app.persistance.entities.DonationDTO;
import com.blooddonorapp.app.persistance.services.mappers.DonationMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DonationService {
    private DonationMapperImpl donationMapper;
    private DonationRepository repository;
    private DonorRepository donorRepository;
    private BloodBankRepository bloodBankRepository;

    @Autowired
    public DonationService(DonationMapperImpl donationMapper, DonationRepository repository, DonorRepository donorRepository, BloodBankRepository bloodBankRepository) {
        this.repository = repository;
        this.donationMapper = donationMapper;
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
        bloodBankRepository.findById(donationDTO.getBloodBankId()).orElseThrow(() -> bloodBankNotFoundException(donationDTO.getBloodBankId()));
        if(donor.getFirstDonationDate() == null){
            donor.setFirstDonationDate(donationDTO.getDonationDate());
            donor.setLastDonationDate(donationDTO.getDonationDate());
        } else {
            List<Donation> donations = donor.getDonations();
            List<Date> donationDates = new ArrayList<>();

            for (Donation item : donations){
                donationDates.add(item.getDonationDate());
            }

            donationDates.add(donationDTO.getDonationDate());

            Collections.sort(donationDates);
            System.out.println(donationDates.get(donations.size()-1));
            donor.setFirstDonationDate(donationDates.get(0));
            donor.setLastDonationDate(donationDates.get(donationDates.size()-1));
        }

        donor.setNumberOfDonations(donor.getDonations().size()+1);
        donor.setPoints(donor.getPoints()+10);

        donorRepository.save(donor);

        Donation donation = repository.save(donationMapper.toMap(donationDTO));

        return donationMapper.toDto(donation);
    }

    public List<DonationDTO> findAll(){
        return donationMapper.toListDto(repository.findAll());
    }

    public DonationDTO findById(final long id){
        Optional<Donation> optionalDonation = repository.findById(id);

        return donationMapper.toDto(optionalDonation.orElseThrow(() -> donationNotFoundException(id)));
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
        if (donationDTO.getDonorId() != null) {
            Donor donor = donorRepository.findById(donationDTO.getDonorId()).orElseThrow(() -> donorNotFoundException(donationDTO.getDonorId()));
            donation.setDonor(donor);
        }
        if (donationDTO.getBloodBankId() != null) {
            BloodBank bloodBank = bloodBankRepository.findById(donationDTO.getBloodBankId()).orElseThrow(() -> bloodBankNotFoundException(donationDTO.getBloodBankId()));
            donation.setBloodBank(bloodBank);
        }
        if (donationDTO.getDonationId() != null){
            donation.setDonationId(donationDTO.getDonationId());
        }
        if (donationDTO.getBloodType() != null){
            donation.setBloodType(BloodType.valueOf(donationDTO.getBloodType()));
        }
        if (donationDTO.getDonationDate() != null){
            donation.setDonationDate(donationDTO.getDonationDate());
        }
        if (donationDTO.getQuantity() != 0) {
            donation.setQuantity(donationDTO.getQuantity());
        }

        return donationMapper.toDto(repository.save(donation));
    }

    public List<DonationDTO> findByDonorId(final Long id){
        Donor donor = donorRepository.findById(id).orElseThrow(() -> donorNotFoundException(id));
        List<Donation> donations = repository.findByDonor(donor);

        Collections.sort(donations, new Comparator<Donation>() {
            @Override
            public int compare(Donation o1, Donation o2) {
                return o2.getDonationDate().compareTo(o1.getDonationDate());
            }
        });

        return donationMapper.toListDto(donations);
    }

    public List<DonationDTO> findByBloodBankId(final Long id){
        BloodBank bloodBank = bloodBankRepository.findById(id).orElseThrow(() -> bloodBankNotFoundException(id));

        return donationMapper.toListDto(repository.findByBloodBank(bloodBank));
    }

    public List<DonationDTO> findByBloodType(final String bloodType){
        return donationMapper.toListDto(repository.findByBloodType(bloodType));
    }

    public List<DonationDTO> findByDonationDate(final Date date) {
        return donationMapper.toListDto(repository.findByDonationDate(date));
    }
}