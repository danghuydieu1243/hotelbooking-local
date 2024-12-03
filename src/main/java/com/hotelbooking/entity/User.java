package com.hotelbooking.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

//    String username;
//    String password;
//    String firstName;
//    String lastName;
//    LocalDate dob;

//    @ManyToMany
//    Set<Role> roles;

    String name;
    String email;
    String password;
    String phone;

    @ManyToMany
    Set<Role> roles;

    LocalDate createdAt;

    @OneToOne(mappedBy = "user")
    ForgotPassword forgotPassword;
}