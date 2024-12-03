package com.hotelbooking.mapper;

import org.mapstruct.Mapper;

import com.hotelbooking.dto.request.RoleRequest;
import com.hotelbooking.dto.response.RoleResponse;
import com.hotelbooking.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}