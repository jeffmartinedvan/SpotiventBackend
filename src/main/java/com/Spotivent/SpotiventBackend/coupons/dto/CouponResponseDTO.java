package com.Spotivent.SpotiventBackend.coupons.dto;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class CouponResponseDTO {
    private Long id;
    private Long eventId;
    private String name;
    private Double discounts;
    private Integer limits;
    private Boolean isReferralPromo;
    private LocalDate startDate;
    private LocalDate endDate;
}