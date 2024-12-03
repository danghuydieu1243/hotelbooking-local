package com.hotelbooking.mapper;

import com.hotelbooking.dto.request.HotelImageCreationRequest;
import com.hotelbooking.dto.request.HotelImageUpdateRequest;
import com.hotelbooking.dto.response.HotelImageResponse;
import com.hotelbooking.entity.HotelImage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HotelImageMapper {
    HotelImage toHotelImage(HotelImageCreationRequest request);
    HotelImageResponse toHotelImageResponse(HotelImage hotelImage);
    void updateHotelImage(@MappingTarget HotelImage hotelImage, HotelImageUpdateRequest request);
}
