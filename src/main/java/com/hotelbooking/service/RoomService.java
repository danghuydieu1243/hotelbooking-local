package com.hotelbooking.service;

import com.hotelbooking.dto.request.RoomCreationRequest;
import com.hotelbooking.dto.request.RoomUpdateRequest;
import com.hotelbooking.dto.response.RoomResponse;
import com.hotelbooking.entity.Room;
import com.hotelbooking.exception.AppException;
import com.hotelbooking.exception.ErrorCode;
import com.hotelbooking.mapper.RoomMapper;
import com.hotelbooking.repository.RoomRepository;
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
public class RoomService {
    RoomRepository roomRepository;
    RoomMapper roomMapper;

    public RoomResponse createRoom(RoomCreationRequest request) {
        Room room = roomMapper.toRoom(request);
        room = roomRepository.save(room);

        return roomMapper.toRoomResponse(room);
    }

    public List<RoomResponse> getRooms() {
        log.info("In method get Rooms");
        return roomRepository.findAll().stream().map(roomMapper::toRoomResponse).toList();
    }

    public RoomResponse getRoom(String id) {
        return roomMapper.toRoomResponse(
                roomRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND)));
    }

    public RoomResponse updateRoom(String roomId, RoomUpdateRequest request) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));

        roomMapper.updateRoom(room, request);

        return roomMapper.toRoomResponse(roomRepository.save(room));
    }

    public void deleteRoom(String roomId) {
        roomRepository.deleteById(roomId);
    }
}
