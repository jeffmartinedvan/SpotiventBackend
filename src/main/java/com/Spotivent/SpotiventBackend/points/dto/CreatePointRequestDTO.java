package com.Spotivent.SpotiventBackend.points.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class CreatePointRequestDTO {
    private Long userId;
    private Long point;
    private Instant expirationDate;
}
