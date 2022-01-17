package com.blooddonorapp.app.persistance.services.mappers;

import com.blooddonorapp.app.models.BloodBank;
import com.blooddonorapp.app.models.Donor;
import com.blooddonorapp.app.models.SystemUser;
import com.blooddonorapp.app.models.UserRole;
import com.blooddonorapp.app.persistance.entities.SystemUserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl {
    public SystemUserDTO toDto(SystemUser user){
        if(user == null){
            return null;
        }

        SystemUserDTO userDTO = new SystemUserDTO();

        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserRole(user.getUserRole().toString());
        if (user.getBloodBank() != null)
            userDTO.setBloodBankId(user.getBloodBank().getBloodBankId());
        if ((user.getDonor() != null))
            userDTO.setDonorId(user.getDonor().getDonorId());

        return userDTO;
    }

    public SystemUser toMap(SystemUserDTO userDTO){
        if(userDTO == null){
            return null;
        }

        SystemUser user = new SystemUser();

        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUserRole(UserRole.valueOf(userDTO.getUserRole()));
        if (userDTO.getBloodBankId() != null)
            user.setBloodBank(new BloodBank(userDTO.getBloodBankId()));
        if (userDTO.getDonorId() != null)
            user.setDonor(new Donor(userDTO.getDonorId()));

        return user;
    }

    public List<SystemUserDTO> toListDto(List<SystemUser> list) {
        if(list == null){
            return null;
        }
        List<SystemUserDTO> listDto = new ArrayList<SystemUserDTO>(list.size());
        for(SystemUser user : list){
            listDto.add(toDto(user));
        }

        return listDto;
    }

    public List<SystemUser> toList(List<SystemUserDTO> listDto) {
        if(listDto == null){
            return null;
        }
        List<SystemUser> list = new ArrayList<SystemUser>(listDto.size());
        for(SystemUserDTO userDTO : listDto){
            list.add(toMap(userDTO));
        }

        return list;
    }
}
