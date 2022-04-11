package com.blooddonorapp.app.persistance.dao;

import com.blooddonorapp.app.models.BloodBank;
import com.blooddonorapp.app.models.Donor;
import com.blooddonorapp.app.models.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<SystemUser, Long> {
    @Override
    SystemUser save(SystemUser user);

    @Override
    List<SystemUser> findAll();

    @Override
    Optional<SystemUser> findById(Long id);

    @Override
    void deleteById(Long id);

    Optional<SystemUser> findByUsername(String username);

    Optional<SystemUser> findByEmail(String email);

    Optional<SystemUser> findByDonor(Donor donor);

    Optional<SystemUser> findByBloodBank(BloodBank bloodBank);
}
