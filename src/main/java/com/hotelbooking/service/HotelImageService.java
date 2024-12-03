package com.hotelbooking.service;

import com.hotelbooking.dto.request.HotelImageCreationRequest;
import com.hotelbooking.dto.request.HotelImageUpdateRequest;
import com.hotelbooking.dto.response.HotelImageResponse;
import com.hotelbooking.entity.HotelImage;
import com.hotelbooking.exception.AppException;
import com.hotelbooking.exception.ErrorCode;
import com.hotelbooking.mapper.HotelImageMapper;
import com.hotelbooking.repository.HotelImageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class HotelImageService {
    HotelImageRepository hotelImageRepository;
    HotelImageMapper hotelImageMapper;

    public HotelImageResponse createHotelImage(HotelImageCreationRequest request) {
        HotelImage hotelImage = hotelImageMapper.toHotelImage(request);
        hotelImage = hotelImageRepository.save(hotelImage);

        return hotelImageMapper.toHotelImageResponse(hotelImage);
    }

    public List<HotelImageResponse> getHotelImages() {
        log.info("In method get HotelImages");
        return hotelImageRepository.findAll().stream().map(hotelImageMapper::toHotelImageResponse).toList();
    }

    public HotelImageResponse getHotelImage(String id) {
        return hotelImageMapper.toHotelImageResponse(
                hotelImageRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.IMAGE_NOT_FOUND)));
    }

    public HotelImageResponse updateHotelImage(String hotelImageId, HotelImageUpdateRequest request) {
        HotelImage hotelImage = hotelImageRepository.findById(hotelImageId).orElseThrow(() -> new AppException(ErrorCode.IMAGE_NOT_FOUND));

        hotelImageMapper.updateHotelImage(hotelImage, request);

        return hotelImageMapper.toHotelImageResponse(hotelImageRepository.save(hotelImage));
    }

    public void deleteHotelImage(String hotelImageId) {
        hotelImageRepository.deleteById(hotelImageId);
    }
}