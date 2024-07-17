package com.Spotivent.SpotiventBackend.transactions.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CreateTransactionRequestDTO {
    private Long eventId;
    private Set<Long> orderItemIds;
    private Long couponId;
    private Long originalPrice;
    private Long totalDiscount;
    private Long usePoints;
    private Long totalPrice;
}