package com.Spotivent.SpotiventBackend.coupons.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateCouponRequestDTO {
    private Long eventId;
    private String name;
    private Double discounts;
    private Integer limits;
    private Boolean isReferralPromo;
    private LocalDate startDate;
    private LocalDate endDate;
}