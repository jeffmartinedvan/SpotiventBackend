package com.Spotivent.SpotiventBackend.points.service;


import com.Spotivent.SpotiventBackend.points.dto.CreatePointRequestDTO;
import com.Spotivent.SpotiventBackend.points.dto.PointResponseDTO;
import com.Spotivent.SpotiventBackend.points.entity.Points;

import java.util.List;

public interface PointService {
    PointResponseDTO createPoints(CreatePointRequestDTO createPointRequestDTO);
    Long getTotalPointsByUserId(Long userId);
    List<PointResponseDTO> getAllPointsByUserId(Long userId);
}
