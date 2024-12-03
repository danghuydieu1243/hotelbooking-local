package com.hotelbooking.mapper;

import com.hotelbooking.dto.request.HotelUpdateRequest;
import com.hotelbooking.dto.request.RoomCreationRequest;
import com.hotelbooking.dto.request.RoomUpdateRequest;
import com.hotelbooking.dto.response.RoomResponse;
import com.hotelbooking.entity.Hotel;
import com.hotelbooking.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    Room toRoom(RoomCreationRequest request);
    RoomResponse toRoomResponse(Room room);
    void updateRoom(@MappingTarget Room room, RoomUpdateRequest request);
}
