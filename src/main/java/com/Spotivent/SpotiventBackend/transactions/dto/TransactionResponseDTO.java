package com.Spotivent.SpotiventBackend.transactions.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class TransactionResponseDTO {
    private Long id;
    private Long userId;
    private Long eventId;
    private Long couponId;
    private Long originalPrice;
    private Long totalDiscount;
    private Long usePoints;
    private Long totalPrice;
    private Instant createdAt;
}