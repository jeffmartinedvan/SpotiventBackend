package com.Spotivent.SpotiventBackend.reviews.controller;

import com.Spotivent.SpotiventBackend.auth.helper.Claims;
import com.Spotivent.SpotiventBackend.reviews.dto.CreateReviewRequestDTO;
import com.Spotivent.SpotiventBackend.reviews.dto.ReviewResponseDTO;
import com.Spotivent.SpotiventBackend.reviews.services.ReviewService;
import com.Spotivent.SpotiventBackend.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create")
    public ResponseEntity<Response<ReviewResponseDTO>> createReview(@RequestBody CreateReviewRequestDTO request) {
        return Response.success("Review created successfully", reviewService.createReview(request));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<Response<List<ReviewResponseDTO>>> getReviewsByEventId(@PathVariable Long eventId) {
        return Response.success("Reviews fetched successfully", reviewService.getReviewsByEventId(eventId));
    }

    @GetMapping("/user")
    public ResponseEntity<Response<List<ReviewResponseDTO>>> getReviewsByUserId() {
        var claims = Claims.getClaims();
        Long userId = (Long) claims.get("id");
        return Response.success("Reviews fetched successfully", reviewService.getReviewsByUserId(userId));
    }
}