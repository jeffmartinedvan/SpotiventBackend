package com.Spotivent.SpotiventBackend.points.service;


import com.Spotivent.SpotiventBackend.points.dto.CreatePointRequestDTO;
import com.Spotivent.SpotiventBackend.points.entity.Points;

public interface PointService {
    Points createPoints(CreatePointRequestDTO createPointRequestDTO);
    Points getByUsersId(Long id);
}
