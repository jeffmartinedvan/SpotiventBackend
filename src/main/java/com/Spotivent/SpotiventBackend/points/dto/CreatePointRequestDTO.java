package com.Spotivent.SpotiventBackend.points.dto;

import lombok.Data;

@Data
public class CreatePointRequestDTO {
    private Long userId;
    private Long point;
}
