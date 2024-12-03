package com.hotelbooking.service;

import com.hotelbooking.dto.request.BookingCreationRequest;
import com.hotelbooking.dto.request.BookingUpdateRequest;
import com.hotelbooking.dto.response.BookingResponse;
import com.hotelbooking.entity.Booking;
import com.hotelbooking.exception.AppException;
import com.hotelbooking.exception.ErrorCode;
import com.hotelbooking.mapper.BookingMapper;
import com.hotelbooking.repository.BookingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookingService {
    BookingMapper bookingMapper;
    BookingRepository bookingRepository;

    public BookingResponse createBooking(BookingCreationRequest request) {
        Booking booking = bookingMapper.toBooking(request);
        booking.setCreatedAt(LocalDate.now());
        booking = bookingRepository.save(booking);

        return bookingMapper.toBookingResponse(booking);
    }

    public List<BookingResponse> getBookings() {
        log.info("In method get Bookings");
        return bookingRepository.findAll().stream().map(bookingMapper::toBookingResponse).toList();
    }

    public BookingResponse getBooking(String id) {
        return bookingMapper.toBookingResponse(
                bookingRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND)));
    }

    public BookingResponse updateBooking(String bookingId, BookingUpdateRequest request) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        bookingMapper.updateBooking(booking, request);

        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

    public void deleteBooking(String bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
