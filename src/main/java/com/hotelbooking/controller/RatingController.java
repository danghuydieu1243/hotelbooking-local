package com.hotelbooking.controller;

import com.hotelbooking.dto.request.RatingCreationRequest;
import com.hotelbooking.dto.request.RatingUpdateRequest;
import com.hotelbooking.dto.response.ApiResponse;
import com.hotelbooking.dto.response.RatingResponse;
import com.hotelbooking.service.RatingService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RatingController {

    RatingService ratingService;

    @PostMapping
    ApiResponse<RatingResponse> createRating(@RequestBody @Valid RatingCreationRequest request) {
        return ApiResponse.<RatingResponse>builder()
                .result(ratingService.createRating(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RatingResponse>> getRatings() {
        return ApiResponse.<List<RatingResponse>>builder()
                .result(ratingService.getRatings())
                .build();
    }

    @GetMapping("/{hotelId}")
    ApiResponse<List<RatingResponse>> getRatingsByHotel(@PathVariable("hotelId") String hotelId) {
        return ApiResponse.<List<RatingResponse>>builder()
                .result(ratingService.getRatingsByHotel(hotelId))
                .build();
    }

    @GetMapping("/{ratingId}")
    ApiResponse<RatingResponse> getRating(@PathVariable("ratingId") String ratingId) {
        return ApiResponse.<RatingResponse>builder()
                .result(ratingService.getRating(ratingId))
                .build();
    }

    @PutMapping("/{ratingId}")
    ApiResponse<RatingResponse> updateRating(@PathVariable String ratingId, @RequestBody RatingUpdateRequest request) {
        return ApiResponse.<RatingResponse>builder()
                .result(ratingService.updateRating(ratingId, request))
                .build();
    }

    @DeleteMapping("/{ratingId}")
    ApiResponse<String> deleteRating(@PathVariable String ratingId) {
        ratingService.deleteRating(ratingId);
        return ApiResponse.<String>builder().result("Rating has been deleted").build();
    }
}
