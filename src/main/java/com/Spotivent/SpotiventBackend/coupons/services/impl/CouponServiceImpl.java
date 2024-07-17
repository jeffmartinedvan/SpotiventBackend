package com.Spotivent.SpotiventBackend.coupons.services.impl;

import com.Spotivent.SpotiventBackend.coupons.dto.CreateCouponRequestDTO;
import com.Spotivent.SpotiventBackend.coupons.dto.CouponResponseDTO;
import com.Spotivent.SpotiventBackend.coupons.entity.Coupons;
import com.Spotivent.SpotiventBackend.coupons.repository.CouponRepository;
import com.Spotivent.SpotiventBackend.coupons.services.CouponService;
import com.Spotivent.SpotiventBackend.events.entity.Events;
import com.Spotivent.SpotiventBackend.events.service.EventService;
import com.Spotivent.SpotiventBackend.exception.ApplicationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final EventService eventService;

    public CouponServiceImpl(CouponRepository couponRepository, EventService eventService) {
        this.couponRepository = couponRepository;
        this.eventService = eventService;
    }

    @Override
    public CouponResponseDTO createCoupon(CreateCouponRequestDTO request) {
        Events event = eventService.getEventById(request.getEventId());

        Coupons coupon = new Coupons();
        coupon.setEvents(event);
        coupon.setName(request.getName());
        coupon.setDiscounts(request.getDiscounts());
        coupon.setLimits(request.getLimits());
        coupon.setIsReferralPromo(request.getIsReferralPromo());
        coupon.setStartDate(request.getStartDate());
        coupon.setEndDate(request.getEndDate());

        Coupons savedCoupon = couponRepository.save(coupon);
        return mapToCouponResponseDTO(savedCoupon);
    }

    @Override
    public List<CouponResponseDTO> getCouponsByEventId(Long eventId) {
        return couponRepository.findByEventsId(eventId)
                .stream()
                .map(this::mapToCouponResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CouponResponseDTO getCouponById(Long id) {
        Coupons coupon = couponRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Coupon not found"));
        return mapToCouponResponseDTO(coupon);
    }

    @Override
    public Coupons getDetailCoupon(Long id) {
        return couponRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Coupon not found"));
    }

    private CouponResponseDTO mapToCouponResponseDTO(Coupons coupon) {
        CouponResponseDTO responseDTO = new CouponResponseDTO();
        responseDTO.setId(coupon.getId());
        responseDTO.setEventId(coupon.getEvents().getId());
        responseDTO.setName(coupon.getName());
        responseDTO.setDiscounts(coupon.getDiscounts());
        responseDTO.setLimits(coupon.getLimits());
        responseDTO.setIsReferralPromo(coupon.getIsReferralPromo());
        responseDTO.setStartDate(coupon.getStartDate());
        responseDTO.setEndDate(coupon.getEndDate());
        return responseDTO;
    }
}