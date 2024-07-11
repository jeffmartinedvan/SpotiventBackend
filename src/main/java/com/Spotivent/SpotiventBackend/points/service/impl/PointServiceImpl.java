package com.Spotivent.SpotiventBackend.points.service.impl;

import com.Spotivent.SpotiventBackend.points.dto.CreatePointRequestDTO;
import com.Spotivent.SpotiventBackend.points.entity.Points;
import com.Spotivent.SpotiventBackend.points.repository.PointRepository;
import com.Spotivent.SpotiventBackend.points.service.PointService;
import com.Spotivent.SpotiventBackend.users.service.UserService;
import org.springframework.stereotype.Service;

import java.awt.*;
@Service
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;
    private final UserService userService;

    public PointServiceImpl(PointRepository pointRepository, UserService userService) {
        this.pointRepository = pointRepository;
        this.userService = userService;
    }

    @Override
    public Points createPoints(CreatePointRequestDTO createPointRequestDTO) {
        Points points = new Points();
        points.setUser(userService.getDetailUser(createPointRequestDTO.getUserId()));
        return null;
    }
}
