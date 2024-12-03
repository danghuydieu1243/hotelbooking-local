package com.hotelbooking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.hotelbooking.dto.request.UserCreationRequest;
import com.hotelbooking.dto.request.UserUpdateRequest;
import com.hotelbooking.dto.response.UserResponse;
import com.hotelbooking.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}