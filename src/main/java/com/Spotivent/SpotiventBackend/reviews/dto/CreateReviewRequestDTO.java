package com.Spotivent.SpotiventBackend.reviews.dto;

import lombok.Data;

@Data
public class CreateReviewRequestDTO {
    private Long userId;
    private Long eventId;
    private Double ratings;
    private String reviews;
}