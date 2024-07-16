package com.Spotivent.SpotiventBackend.reviews.repository;

import com.Spotivent.SpotiventBackend.reviews.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, Long> {
    List<Reviews> findByEventsId(Long eventId);
    List<Reviews> findByUsersId(Long userId);
}