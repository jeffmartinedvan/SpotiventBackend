package com.Spotivent.SpotiventBackend.reviews.services;

import com.Spotivent.SpotiventBackend.reviews.dto.CreateReviewRequestDTO;
import com.Spotivent.SpotiventBackend.reviews.dto.ReviewResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ReviewService {
    ReviewResponseDTO createReview(CreateReviewRequestDTO request);
    List<ReviewResponseDTO> getReviewsByEventId(Long eventId);
    List<ReviewResponseDTO> getReviewsByUserId(Long userId);
}