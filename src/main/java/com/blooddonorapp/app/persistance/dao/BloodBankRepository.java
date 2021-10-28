package com.blooddonorapp.app.persistance.dao;

import com.blooddonorapp.app.models.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {
    @Override
    BloodBank save(BloodBank bloodBank);

    @Override
    List<BloodBank> findAll();

    @Override
    Optional<BloodBank> findById(Long id);

    @Override
    void deleteById(Long id);

    Optional<BloodBank> findByUsername(String username);

    Optional<BloodBank> findByEmail(String email);

    List<BloodBank> findByCity(String city);

    Optional<BloodBank> findByDonorId(Long id);
}
