package com.Spotivent.SpotiventBackend.coupons.services;

import com.Spotivent.SpotiventBackend.coupons.dto.CreateCouponRequestDTO;
import com.Spotivent.SpotiventBackend.coupons.dto.CouponResponseDTO;

import java.util.List;

public interface CouponService {
    CouponResponseDTO createCoupon(CreateCouponRequestDTO request);
    List<CouponResponseDTO> getCouponsByEventId(Long eventId);
    CouponResponseDTO getCouponById(Long id);
}