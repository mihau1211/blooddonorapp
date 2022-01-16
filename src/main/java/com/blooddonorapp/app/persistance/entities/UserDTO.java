package com.blooddonorapp.app.persistance.entities;

import lombok.*;

import javax.persistence.Id;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class UserDTO {
    @Id
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String role;
}
