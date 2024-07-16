package com.Spotivent.SpotiventBackend.points.dto;

import lombok.Data;

@Data
public class PointResponseDTO {
    private Long id;
    private Long userId;
    private Long point;
}