package com.Spotivent.SpotiventBackend.points.repository;

import com.Spotivent.SpotiventBackend.points.entity.Points;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Points, Long> {
    Points findByUsersId(Long id);
    List<Points> findAllByUsersId(Long userId);
}
