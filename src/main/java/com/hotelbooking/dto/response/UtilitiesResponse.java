package com.hotelbooking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UtilitiesResponse {
    String id;
    String name;
    String imageUrl;
}
