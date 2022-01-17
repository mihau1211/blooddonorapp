package com.blooddonorapp.app.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private String email;
    private String password;
    private UserRole userRole;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    BloodBank bloodBank;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    Donor donor;
}
