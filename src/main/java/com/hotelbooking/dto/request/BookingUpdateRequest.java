package com.hotelbooking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingUpdateRequest {
    String roomId;
    String customerId;
    String checkInDate;
    String checkOutDate;
    String status;
}
