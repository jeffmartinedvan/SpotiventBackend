package com.Spotivent.SpotiventBackend.points.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class PointResponseDTO {
    private Long id;
    private Long userId;
    private Long point;
    private Instant expirationDate;
}