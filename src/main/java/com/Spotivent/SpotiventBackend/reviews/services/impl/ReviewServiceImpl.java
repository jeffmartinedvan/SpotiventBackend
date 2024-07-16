package com.Spotivent.SpotiventBackend.reviews.services.impl;

import com.Spotivent.SpotiventBackend.events.entity.Events;
import com.Spotivent.SpotiventBackend.events.service.EventService;
import com.Spotivent.SpotiventBackend.reviews.dto.CreateReviewRequestDTO;
import com.Spotivent.SpotiventBackend.reviews.dto.ReviewResponseDTO;
import com.Spotivent.SpotiventBackend.reviews.entity.Reviews;
import com.Spotivent.SpotiventBackend.reviews.repository.ReviewRepository;
import com.Spotivent.SpotiventBackend.reviews.services.ReviewService;
import com.Spotivent.SpotiventBackend.users.entity.Users;
import com.Spotivent.SpotiventBackend.users.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final EventService eventService;
    private final UserService userService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, EventService eventService, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.eventService = eventService;
        this.userService = userService;
    }

    @Override
    public ReviewResponseDTO createReview(CreateReviewRequestDTO request) {
        Users user = userService.getDetailUser(request.getUserId());
        Events event = eventService.getEventById(request.getEventId());

        Reviews review = new Reviews();
        review.setEvents(event);
        review.setUsers(user);
        review.setRatings(request.getRatings());
        review.setReviews(request.getReviews());

        return mapToReviewResponseDTO(reviewRepository.save(review));
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByEventId(Long eventId) {
        return reviewRepository.findByEventsId(eventId)
                .stream()
                .map(this::mapToReviewResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUsersId(userId)
                .stream()
                .map(this::mapToReviewResponseDTO)
                .collect(Collectors.toList());
    }

    private ReviewResponseDTO mapToReviewResponseDTO(Reviews review) {
        ReviewResponseDTO responseDTO = new ReviewResponseDTO();
        responseDTO.setId(review.getId());
        responseDTO.setEventId(review.getEvents().getId());
        responseDTO.setUserId(review.getUsers().getId());
        responseDTO.setRatings(review.getRatings());
        responseDTO.setReviews(review.getReviews());
        responseDTO.setCreatedAt(review.getCreatedAt());
        return responseDTO;
    }
}