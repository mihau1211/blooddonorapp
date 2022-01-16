package com.blooddonorapp.app.persistance.dao;

import com.blooddonorapp.app.models.BloodType;
import com.blooddonorapp.app.models.Donor;
import com.blooddonorapp.app.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    List<Donor> findByGender(Gender gender);

    List<Donor> findByBloodType(BloodType bloodType);

    List<Donor> findByCity(String city);

    @Query(value = "SELECT * FROM donor WHERE birthdate = :birthdate",
            nativeQuery = true)
    List<Donor> findByBirthdate(Date birthdate);
}
