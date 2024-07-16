package com.Spotivent.SpotiventBackend.coupons.repository;

import com.Spotivent.SpotiventBackend.coupons.entity.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupons, Long> {
    List<Coupons> findByEventsId(Long eventId);
}