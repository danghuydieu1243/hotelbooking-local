package com.hotelbooking.mapper;

import com.hotelbooking.dto.request.BookingCreationRequest;
import com.hotelbooking.dto.request.BookingUpdateRequest;
import com.hotelbooking.dto.response.BookingResponse;
import com.hotelbooking.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking toBooking(BookingCreationRequest request);
    BookingResponse toBookingResponse(Booking booking);
    void updateBooking(@MappingTarget Booking booking, BookingUpdateRequest request);
}

