package com.hotelbooking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingCreationRequest {
    String roomId;
    String customerId;
    String checkInDate;
    String checkOutDate;
    String status;
}
