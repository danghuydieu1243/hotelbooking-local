package com.hotelbooking.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String ownerId;
    String name;
    String street;
    String district;
    String city;

    @Lob
    String description;
    @Builder.Default
    Double rating = 0.0;
    @ManyToMany
    Set<Utilities> utilities;
    @ManyToMany
    Set<HotelImage> hotelImages;
}
