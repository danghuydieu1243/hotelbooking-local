package com.hotelbooking.repository;

import com.hotelbooking.entity.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelImageRepository extends JpaRepository<HotelImage, String> {
}
