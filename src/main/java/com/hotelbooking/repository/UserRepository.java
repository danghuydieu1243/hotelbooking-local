package com.hotelbooking.repository;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotelbooking.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
//    boolean existsByUsername(String username);
//
//    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User u set u.password = ?2 where u.email = ?1")
    void updatePassword(String email, String password);
}