package com.hotelbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    HOTEL_NOT_FOUND(1009, "Hotel not found", HttpStatus.NOT_FOUND),
    ROOM_NOT_FOUND(1010, "Room not found", HttpStatus.NOT_FOUND),
    BOOKING_NOT_FOUND(1011, "Booking not found", HttpStatus.NOT_FOUND),
    UTILITIES_NOT_FOUND(1012, "Utilities not found", HttpStatus.NOT_FOUND),
    RATINGS_NOT_FOUND(1013, "Ratings not found", HttpStatus.NOT_FOUND),
    IMAGE_NOT_FOUND(1014, "Image not found", HttpStatus.NOT_FOUND),
    INVOICE_NOT_FOUND(1015, "Invoice not found", HttpStatus.NOT_FOUND),
    INVALID_OTP(1016, "Invalid OTP for email", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}