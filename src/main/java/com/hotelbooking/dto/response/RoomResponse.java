package com.hotelbooking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomResponse {
    String id;
    String hotelId;
    String name;
    String description;
    String price;
    Boolean available;
    String imageLocation;
}
