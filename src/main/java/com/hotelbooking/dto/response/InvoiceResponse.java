package com.hotelbooking.dto.response;

import com.hotelbooking.common.PaymentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceResponse {
    String id;
    String userId;
    Double totalAmount;
    LocalDate creationDate;
    PaymentStatus paymentStatus;
}
