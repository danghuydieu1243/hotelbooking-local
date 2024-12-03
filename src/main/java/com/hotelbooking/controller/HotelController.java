package com.hotelbooking.controller;

import com.hotelbooking.dto.request.HotelCreationRequest;
import com.hotelbooking.dto.request.HotelUpdateRequest;
import com.hotelbooking.dto.response.ApiResponse;
import com.hotelbooking.dto.response.HotelResponse;
import com.hotelbooking.service.HotelService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class HotelController {
    HotelService hotelService;

    @PostMapping
    ApiResponse<HotelResponse> createHotel(@RequestBody @Valid HotelCreationRequest request) {
        return ApiResponse.<HotelResponse>builder()
                .result(hotelService.createHotel(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<HotelResponse>> getHotels() {
        return ApiResponse.<List<HotelResponse>>builder()
                .result(hotelService.getHotels())
                .build();
    }

    @GetMapping("/{hotelId}")
    ApiResponse<HotelResponse> getHotel(@PathVariable("hotelId") String hotelId) {
        return ApiResponse.<HotelResponse>builder()
                .result(hotelService.getHotel(hotelId))
                .build();
    }

    @PutMapping("/{hotelId}")
    ApiResponse<HotelResponse> updateHotel(@PathVariable String hotelId, @RequestBody HotelUpdateRequest request) {
        return ApiResponse.<HotelResponse>builder()
                .result(hotelService.updateHotel(hotelId, request))
                .build();
    }

    @DeleteMapping("/{hotelId}")
    ApiResponse<String> deleteHotel(@PathVariable String hotelId) {
        hotelService.deleteHotel(hotelId);
        return ApiResponse.<String>builder().result("Hotel has been deleted").build();
    }
}
