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
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String hotelId;
    String userId;
    Double point;
    String comment;
    LocalDate createdAt;
}
