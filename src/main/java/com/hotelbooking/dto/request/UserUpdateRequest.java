package com.hotelbooking.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.hotelbooking.validator.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
//    String password;
//    String firstName;
//    String lastName;
//
//    @DobConstraint(min = 18, message = "INVALID_DOB")
//    LocalDate dob;
//
//    List<String> roles;

    String name;
    String password;
    String phone;
    List<String> roles;
}
