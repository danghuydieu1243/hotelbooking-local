package com.hotelbooking.controller;

import com.hotelbooking.dto.request.HotelImageCreationRequest;
import com.hotelbooking.dto.request.HotelImageUpdateRequest;
import com.hotelbooking.dto.response.ApiResponse;
import com.hotelbooking.dto.response.HotelImageResponse;
import com.hotelbooking.service.HotelImageService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel-images")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class HotelImageController {
    HotelImageService hotelImageService;

    @PostMapping
    ApiResponse<HotelImageResponse> createHotelImage(@RequestBody @Valid HotelImageCreationRequest request) {
        return ApiResponse.<HotelImageResponse>builder()
                .result(hotelImageService.createHotelImage(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<HotelImageResponse>> getHotelImages() {
        return ApiResponse.<List<HotelImageResponse>>builder()
                .result(hotelImageService.getHotelImages())
                .build();
    }

    @GetMapping("/{hotelImageId}")
    ApiResponse<HotelImageResponse> getHotelImage(@PathVariable("hotelImageId") String hotelImageId) {
        return ApiResponse.<HotelImageResponse>builder()
                .result(hotelImageService.getHotelImage(hotelImageId))
                .build();
    }

    @PutMapping("/{hotelImageId}")
    ApiResponse<HotelImageResponse> updateHotelImage(@PathVariable String hotelImageId, @RequestBody HotelImageUpdateRequest request) {
        return ApiResponse.<HotelImageResponse>builder()
                .result(hotelImageService.updateHotelImage(hotelImageId, request))
                .build();
    }

    @DeleteMapping("/{hotelImageId}")
    ApiResponse<String> deleteHotelImage(@PathVariable String hotelImageId) {
        hotelImageService.deleteHotelImage(hotelImageId);
        return ApiResponse.<String>builder().result("HotelImage has been deleted").build();
    }
}
