package com.Spotivent.SpotiventBackend.points.service;


import com.Spotivent.SpotiventBackend.points.dto.CreatePointRequestDTO;
import com.Spotivent.SpotiventBackend.points.entity.Points;
import org.springframework.stereotype.Service;

@Service
public interface PointService {
    Points createPoints(CreatePointRequestDTO createPointRequestDTO);
}
