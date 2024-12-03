package com.hotelbooking.mapper;

import com.hotelbooking.dto.request.HotelCreationRequest;
import com.hotelbooking.dto.request.HotelUpdateRequest;
import com.hotelbooking.dto.response.HotelResponse;
import com.hotelbooking.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    Hotel toHotel(HotelCreationRequest request);
    HotelResponse toHotelResponse(Hotel hotel);
    void updateHotel(@MappingTarget Hotel hotel, HotelUpdateRequest request);
}
