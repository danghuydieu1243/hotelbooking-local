package com.hotelbooking.controller;

import com.hotelbooking.dto.request.BookingCreationRequest;
import com.hotelbooking.dto.request.BookingUpdateRequest;
import com.hotelbooking.dto.response.ApiResponse;
import com.hotelbooking.dto.response.BookingResponse;
import com.hotelbooking.service.BookingService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookingController {
    BookingService bookingService;

    @PostMapping
    ApiResponse<BookingResponse> createBooking(@RequestBody @Valid BookingCreationRequest request) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.createBooking(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<BookingResponse>> getBookings() {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getBookings())
                .build();
    }

    @GetMapping("/{bookingId}")
    ApiResponse<BookingResponse> getBooking(@PathVariable("bookingId") String bookingId) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.getBooking(bookingId))
                .build();
    }

    @PutMapping("/{bookingId}")
    ApiResponse<BookingResponse> updateBooking(@PathVariable String bookingId, @RequestBody BookingUpdateRequest request) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.updateBooking(bookingId, request))
                .build();
    }

    @DeleteMapping("/{bookingId}")
    ApiResponse<String> deleteBooking(@PathVariable String bookingId) {
        bookingService.deleteBooking(bookingId);
        return ApiResponse.<String>builder().result("Booking has been deleted").build();
    }
}
