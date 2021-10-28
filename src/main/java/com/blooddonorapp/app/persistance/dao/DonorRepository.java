package com.blooddonorapp.app.persistance.dao;

import com.blooddonorapp.app.models.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
    @Override
    Donor save(Donor donor);

    @Override
    List<Donor> findAll();

    @Override
    Optional<Donor> findById(Long id);

    @Override
    void deleteById(Long id);

    Optional<Donor> findByUsername(String username);

    Optional<Donor> findByEmail(String email);

    List<Donor> findBySurname(String surname);

    List<Donor> findByGender(String gender);

    List<Donor> findByBloodType(String bloodType);

    List<Donor> findByCity(String city);

    Optional<Donor> findDonorByDonationId(Long id);
}
