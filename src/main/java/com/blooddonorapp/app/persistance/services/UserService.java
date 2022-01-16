package com.blooddonorapp.app.persistance.services;

import com.blooddonorapp.app.exceptions.user.UserEmailNotFoundException;
import com.blooddonorapp.app.exceptions.user.UserNotFoundException;
import com.blooddonorapp.app.exceptions.user.UserUsernameNotFoundException;
import com.blooddonorapp.app.models.User;
import com.blooddonorapp.app.persistance.dao.UserRepository;
import com.blooddonorapp.app.persistance.entities.UserDTO;
import com.blooddonorapp.app.persistance.services.mappers.UserMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository repository;
    private UserMapperImpl mapper;

    @Autowired
    public UserService(UserRepository repository, UserMapperImpl mapper){
        this.repository = repository;
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

    public UserDTO save(final UserDTO userDTO){
        User user = repository.save(mapper.toMap(userDTO));

        return mapper.toDto(user);
    }

    public UserDTO findById(final Long id){
        Optional<User> optionalUser = repository.findById(id);

        return mapper.toDto(optionalUser.orElseThrow(() -> userNotFoundException(id)));
    }

    public List<UserDTO> findAll() {
        return mapper.toListDto(repository.findAll());
    }

    public void deleteById(final Long id) {
        try{
            repository.deleteById(id);
        }catch (Exception e){
            throw userNotFoundException(id);
        }
    }

    public UserDTO update(final Long id, final UserDTO userDTO){
        User user = repository.findById(id).orElseThrow(() -> userNotFoundException(id));

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

    public UserDTO findByUsername(final String username) {
        Optional<User> optionalUser = repository.findByUsername(username);

        return mapper.toDto(optionalUser.orElseThrow(() -> userUsernameNotFoundException(username)));
    }

    public UserDTO findByEmail(final String email){
        Optional<User> optionalUser = repository.findByEmail(email);

        return mapper.toDto(optionalUser.orElseThrow(() -> userEmailNotFoundException(email)));
    }
}
