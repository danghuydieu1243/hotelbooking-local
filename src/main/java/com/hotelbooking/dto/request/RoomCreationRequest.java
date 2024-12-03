package com.hotelbooking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomCreationRequest {
    String hotelId;
    String name;
    String description;
    String price;
    Boolean available;
    String imageLocation;
}
