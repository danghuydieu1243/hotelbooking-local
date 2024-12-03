package com.hotelbooking.repository;

import com.hotelbooking.entity.Utilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilitiesRepository extends JpaRepository<Utilities,String> {
}
