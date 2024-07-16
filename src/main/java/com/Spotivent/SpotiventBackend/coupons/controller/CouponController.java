package com.Spotivent.SpotiventBackend.coupons.controller;

import com.Spotivent.SpotiventBackend.coupons.dto.CreateCouponRequestDTO;
import com.Spotivent.SpotiventBackend.coupons.dto.CouponResponseDTO;
import com.Spotivent.SpotiventBackend.coupons.services.CouponService;
import com.Spotivent.SpotiventBackend.response.Response;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/create")
    public ResponseEntity<Response<CouponResponseDTO>> createCoupon(@RequestBody CreateCouponRequestDTO request) {
        return Response.success("Coupon created successfully", couponService.createCoupon(request));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<Response<List<CouponResponseDTO>>> getCouponsByEventId(@PathVariable Long eventId) {
        return Response.success("Coupons fetched successfully", couponService.getCouponsByEventId(eventId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<CouponResponseDTO>> getCouponById(@PathVariable Long id) {
        return Response.success("Coupon fetched successfully", couponService.getCouponById(id));
    }
}