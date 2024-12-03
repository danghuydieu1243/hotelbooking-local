package com.hotelbooking.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ForgotPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String Id;

    @Column(nullable = false)
    Integer otp;
    @Column(nullable = false)
    Date expirationTime;

    @OneToOne
    User user;
}
