package com.Spotivent.SpotiventBackend.reviews.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ReviewResponseDTO {
    private Long id;
    private Long eventId;
    private Long userId;
    private Double ratings;
    private String reviews;
    private Instant createdAt;
}