package com.blooddonorapp.app.persistance.dao;

import com.blooddonorapp.app.models.BloodBank;
import com.blooddonorapp.app.models.Donation;
import com.blooddonorapp.app.models.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Override
    Donation save(Donation donation);

    @Override
    List<Donation> findAll();

    @Override
    Optional<Donation> findById(Long id);

    @Override
    void deleteById(Long id);

    List<Donation> findByDonor(Donor donor);

    List<Donation> findByBloodBank(BloodBank bloodBank);

    List<Donation> findByBloodType(String bloodType);

    List<Donation> findByDonationDate(Date date);
}
