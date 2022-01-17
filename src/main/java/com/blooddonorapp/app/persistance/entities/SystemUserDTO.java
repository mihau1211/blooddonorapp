package com.blooddonorapp.app.persistance.entities;

import com.blooddonorapp.app.models.BloodBank;
import lombok.*;

import javax.persistence.Id;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class SystemUserDTO {
    @Id
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String userRole;
    private Date lastLogin;
    private Long bloodBankId;
    private Long donorId;
}
