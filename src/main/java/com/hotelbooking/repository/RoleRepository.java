package com.hotelbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelbooking.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}