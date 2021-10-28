package com.blooddonorapp.app.persistance.dao;

import com.blooddonorapp.app.models.Donation;
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

    List<Donation> findByDonorId(Long id);

    List<Donation> findByBloodBankId(Long id);

    List<Donation> findByBloodType(String bloodType);

    List<Donation> findByDate(Date date);
}
