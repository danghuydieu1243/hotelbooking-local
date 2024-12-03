package com.hotelbooking.service;

import com.hotelbooking.dto.request.HotelCreationRequest;
import com.hotelbooking.dto.request.HotelUpdateRequest;
import com.hotelbooking.dto.response.HotelResponse;
import com.hotelbooking.entity.Hotel;
import com.hotelbooking.exception.AppException;
import com.hotelbooking.exception.ErrorCode;
import com.hotelbooking.mapper.HotelMapper;
import com.hotelbooking.repository.HotelRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class HotelService {
    HotelRepository hotelRepository;
    HotelMapper hotelMapper;

    public HotelResponse createHotel(HotelCreationRequest request) {
        Hotel hotel = hotelMapper.toHotel(request);
        hotel = hotelRepository.save(hotel);

        return hotelMapper.toHotelResponse(hotel);
    }

    public List<HotelResponse> getHotels() {
        log.info("In method get Hotels");
        return hotelRepository.findAll().stream().map(hotelMapper::toHotelResponse).toList();
    }

    public HotelResponse getHotel(String id) {
        return hotelMapper.toHotelResponse(
                hotelRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_FOUND)));
    }

    public HotelResponse updateHotel(String hotelId, HotelUpdateRequest request) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_FOUND));

        hotelMapper.updateHotel(hotel, request);

        return hotelMapper.toHotelResponse(hotelRepository.save(hotel));
    }

    public void deleteHotel(String hotelId) {
        hotelRepository.deleteById(hotelId);
    }
}
