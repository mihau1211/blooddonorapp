package com.blooddonorapp.app.persistance.services.mappers;

import com.blooddonorapp.app.models.User;
import com.blooddonorapp.app.persistance.entities.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl {
    public UserDTO toDto(User user){
        if(user == null){
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    public User toMap(UserDTO userDTO){
        if(userDTO == null){
            return null;
        }

        User user = new User();

        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        return user;
    }

    public List<UserDTO> toListDto(List<User> list) {
        if(list == null){
            return null;
        }
        List<UserDTO> listDto = new ArrayList<UserDTO>(list.size());
        for(User user : list){
            listDto.add(toDto(user));
        }

        return listDto;
    }

    public List<User> toList(List<UserDTO> listDto) {
        if(listDto == null){
            return null;
        }
        List<User> list = new ArrayList<User>(listDto.size());
        for(UserDTO userDTO : listDto){
            list.add(toMap(userDTO));
        }

        return list;
    }
}
