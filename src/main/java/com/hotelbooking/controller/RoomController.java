package com.hotelbooking.controller;

import com.hotelbooking.dto.request.RoomCreationRequest;
import com.hotelbooking.dto.request.RoomUpdateRequest;
import com.hotelbooking.dto.response.ApiResponse;
import com.hotelbooking.dto.response.RoomResponse;
import com.hotelbooking.service.RoomService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoomController {
    RoomService roomService;

    @PostMapping
    ApiResponse<RoomResponse> createRoom(@RequestBody @Valid RoomCreationRequest request) {
        return ApiResponse.<RoomResponse>builder()
                .result(roomService.createRoom(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoomResponse>> getRooms() {
        return ApiResponse.<List<RoomResponse>>builder()
                .result(roomService.getRooms())
                .build();
    }

    @GetMapping("/{roomId}")
    ApiResponse<RoomResponse> getRoom(@PathVariable("roomId") String roomId) {
        return ApiResponse.<RoomResponse>builder()
                .result(roomService.getRoom(roomId))
                .build();
    }

    @PutMapping("/{roomId}")
    ApiResponse<RoomResponse> updateRoom(@PathVariable String roomId, @RequestBody RoomUpdateRequest request) {
        return ApiResponse.<RoomResponse>builder()
                .result(roomService.updateRoom(roomId, request))
                .build();
    }

    @DeleteMapping("/{roomId}")
    ApiResponse<String> deleteRoom(@PathVariable String roomId) {
        roomService.deleteRoom(roomId);
        return ApiResponse.<String>builder().result("Room has been deleted").build();
    }
}
