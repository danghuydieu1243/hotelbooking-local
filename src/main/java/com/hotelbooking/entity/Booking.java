package com.hotelbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String roomId;
    String customerId;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    String status;
    LocalDate createdAt;
}
