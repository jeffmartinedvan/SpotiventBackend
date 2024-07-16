package com.Spotivent.SpotiventBackend.points.repository;

import com.Spotivent.SpotiventBackend.points.entity.Points;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface PointRepository extends JpaRepository<Points, Long> {
    Points findByUsersId(Long id);
    List<Points> findAllByUsersId(Long userId);
    @Modifying
    @Query("DELETE FROM Points p WHERE p.expirationDate < :currentDate")
    void deleteExpiredPoints(Instant currentDate);
}
