package com.hotelbooking.dto.request;

import com.hotelbooking.entity.HotelImage;
import com.hotelbooking.entity.Utilities;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelCreationRequest {
    String ownerId;
    String name;
    String street;
    String district;
    String city;
    String description;
    List<Utilities> utilities;
    List<HotelImage> hotelImages;
}
