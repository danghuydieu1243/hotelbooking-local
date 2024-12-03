package com.hotelbooking.controller;

import com.hotelbooking.dto.request.UtilitiesCreationRequest;
import com.hotelbooking.dto.request.UtilitiesUpdateRequest;
import com.hotelbooking.dto.response.ApiResponse;
import com.hotelbooking.dto.response.UtilitiesResponse;
import com.hotelbooking.service.UtilitiesService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilities")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UtilitiesController {
    UtilitiesService utilitiesService;

    @PostMapping
    ApiResponse<UtilitiesResponse> createUtilities(@RequestBody @Valid UtilitiesCreationRequest request) {
        return ApiResponse.<UtilitiesResponse>builder()
                .result(utilitiesService.createUtilities(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<UtilitiesResponse>> getListUtilities() {
        return ApiResponse.<List<UtilitiesResponse>>builder()
                .result(utilitiesService.getListUtilities())
                .build();
    }

    @GetMapping("/{utilitiesId}")
    ApiResponse<UtilitiesResponse> getUtilities(@PathVariable("utilitiesId") String utilitiesId) {
        return ApiResponse.<UtilitiesResponse>builder()
                .result(utilitiesService.getUtilities(utilitiesId))
                .build();
    }

    @PutMapping("/{utilitiesId}")
    ApiResponse<UtilitiesResponse> updateUtilities(@PathVariable String utilitiesId, @RequestBody UtilitiesUpdateRequest request) {
        return ApiResponse.<UtilitiesResponse>builder()
                .result(utilitiesService.updateUtilities(utilitiesId, request))
                .build();
    }

    @DeleteMapping("/{utilitiesId}")
    ApiResponse<String> deleteUtilities(@PathVariable String utilitiesId) {
        utilitiesService.deleteUtilities(utilitiesId);
        return ApiResponse.<String>builder().result("Utilities has been deleted").build();
    }
}
