package com.hotelbooking.service;

import java.util.HashSet;
import java.util.List;

import com.hotelbooking.entity.Role;
import org.springframework.stereotype.Service;

import com.hotelbooking.dto.request.RoleRequest;
import com.hotelbooking.dto.response.RoleResponse;
import com.hotelbooking.mapper.RoleMapper;
import com.hotelbooking.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request) {
        Role role = roleMapper.toRole(request);

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}