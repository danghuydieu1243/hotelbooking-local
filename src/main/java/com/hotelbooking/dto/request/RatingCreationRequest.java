package com.hotelbooking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingCreationRequest {
    String hotelId;
    String userId;
    Double point;
    String comment;
}
