package com.example.applicationonlineshop.entity;


import com.example.applicationonlineshop.enums.Role;
import com.example.applicationonlineshop.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class AuthUser extends Auditable {


    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phonenumber;


    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;


    private String activationCode;

    @Builder(builderMethodName = "childBuilderName")

    public AuthUser(Integer id, Timestamp createdAt, Timestamp updatedAt, String username, String email, String password, String phonenumber, Role role, Status status, String activationCode) {
        super(id, createdAt, updatedAt);
        this.username = username;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.role = role;
        this.status = status;
        this.activationCode = activationCode;
    }
}
