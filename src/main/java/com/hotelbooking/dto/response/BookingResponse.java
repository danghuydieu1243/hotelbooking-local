package com.hotelbooking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    String id;
    String roomId;
    String customerId;
    String checkInDate;
    String checkOutDate;
    String status;
    LocalDate createdAt;
}
