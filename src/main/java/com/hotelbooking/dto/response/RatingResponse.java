package com.hotelbooking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingResponse {
    String id;
    String hotelId;
    String userId;
    Double point;
    String comment;
    LocalDate createdAt;
}
