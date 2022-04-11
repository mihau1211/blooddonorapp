package com.blooddonorapp.app.persistance.services;

import com.blooddonorapp.app.exceptions.user.*;
import com.blooddonorapp.app.models.*;
import com.blooddonorapp.app.persistance.dao.BloodBankRepository;
import com.blooddonorapp.app.persistance.dao.DonorRepository;
import com.blooddonorapp.app.persistance.dao.UserRepository;
import com.blooddonorapp.app.persistance.entities.BloodBankDTO;
import com.blooddonorapp.app.persistance.entities.SystemUserDTO;
import com.blooddonorapp.app.persistance.services.mappers.BloodBankMapperImpl;
import com.blooddonorapp.app.persistance.services.mappers.DonorMapperImpl;
import com.blooddonorapp.app.persistance.services.mappers.UserMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class UserService {
    private UserRepository repository;
    private DonorRepository donorRepository;
    private BloodBankRepository bloodBankRepository;
    private UserMapperImpl mapper;
    private DonorMapperImpl donorMapper;
    private BloodBankMapperImpl bloodBankMapper;

    @Autowired
    public UserService(UserRepository repository, DonorRepository donorRepository, BloodBankRepository bloodBankRepository, UserMapperImpl mapper){
        this.repository = repository;
        this.donorRepository = donorRepository;
        this.bloodBankRepository = bloodBankRepository;
        this.mapper = mapper;
    }

    private UserNotFoundException userNotFoundException(Long id){
        return new UserNotFoundException(id);
    }

    private UserUsernameNotFoundException userUsernameNotFoundException(String username){
        return new UserUsernameNotFoundException(username);
    }

    private UserEmailNotFoundException userEmailNotFoundException(String email){
        return new UserEmailNotFoundException(email);
    }

    private DonorIdNotFoundException donorIdNotFoundException(Long donorId) {
        return new DonorIdNotFoundException(donorId);
    }

    private BloodBankIdNotFoundException bloodBankIdNotFoundException(Long bloodBankId) {
        return new BloodBankIdNotFoundException(bloodBankId);
    }

    public SystemUserDTO save(final SystemUserDTO userDTO){
        SystemUser user = repository.save(mapper.toMap(userDTO));

        return mapper.toDto(user);
    }

    public SystemUserDTO findById(final Long id){
        Optional<SystemUser> optionalUser = repository.findById(id);

        return mapper.toDto(optionalUser.orElseThrow(() -> userNotFoundException(id)));
    }

    public List<SystemUserDTO> findAll() {
        return mapper.toListDto(repository.findAll());
    }

    public void deleteById(final Long id) {
        try{
            repository.deleteById(id);
        }catch (Exception e){
            throw userNotFoundException(id);
        }
    }

    public SystemUserDTO update(final Long id, final SystemUserDTO userDTO){
        SystemUser user = repository.findById(id).orElseThrow(() -> userNotFoundException(id));

        if (userDTO.getUserId() != null){
            user.setUserId(userDTO.getUserId());
        }
        if (userDTO.getEmail() != null){
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPassword() != null){
            user.setPassword(userDTO.getPassword());
        }

        return mapper.toDto(repository.save(user));
    }

    public SystemUserDTO findByUsername(final String username) {
        Optional<SystemUser> optionalUser = repository.findByUsername(username);

        return mapper.toDto(optionalUser.orElseThrow(() -> userUsernameNotFoundException(username)));
    }

    public SystemUserDTO findByEmail(final String email){
        Optional<SystemUser> optionalUser = repository.findByEmail(email);

        return mapper.toDto(optionalUser.orElseThrow(() -> userEmailNotFoundException(email)));
    }

    public SystemUserDTO findByDonorId(final Long donorId){
        Optional<Donor> donorOptional = donorRepository.findById(donorId);
        Optional<SystemUser> optionalUser = repository.findByDonor(donorOptional.get());

        return mapper.toDto(optionalUser.orElseThrow(() -> donorIdNotFoundException(donorId)));
    }

    public SystemUserDTO findByBloodBankId(final Long bloodBankId){
        Optional<BloodBank> bloodBankOptional = bloodBankRepository.findById(bloodBankId);
        Optional<SystemUser> optionalUser = repository.findByBloodBank(bloodBankOptional.get());

        return mapper.toDto(optionalUser.orElseThrow(() -> bloodBankIdNotFoundException(bloodBankId)));
    }

    public SystemUserDTO login(final SystemUserDTO userDTO){
        Optional<SystemUser> user = repository.findByEmail(userDTO.getEmail());
        if (user.isEmpty()) {
            throw new UserEmailNotFoundException(userDTO.getEmail());
        }
        if(user.get().getPassword().equals(userDTO.getPassword())) {
            return mapper.toDto(user.get());
        } else {
            return null;
        }
    }

    public void sendMail(final Email email) {
        Properties properties = System.getProperties();

        String host = "smtp.gmail.com";

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("bda.zgrckik@gmail.com", "Qwerty1@");
            }
        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(email.getFrom());
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
            message.setSubject(email.getSubject());
            message.setText(email.getMessage());
            Transport.send(message);
        } catch(MessagingException me) {
            me.printStackTrace();
        }
    }
}