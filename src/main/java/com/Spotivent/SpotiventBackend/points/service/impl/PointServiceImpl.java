package com.Spotivent.SpotiventBackend.points.service.impl;

import com.Spotivent.SpotiventBackend.points.dto.CreatePointRequestDTO;
import com.Spotivent.SpotiventBackend.points.entity.Points;
import com.Spotivent.SpotiventBackend.points.repository.PointRepository;
import com.Spotivent.SpotiventBackend.points.service.PointService;
import com.Spotivent.SpotiventBackend.referrals.entity.Referrals;
import com.Spotivent.SpotiventBackend.users.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.awt.*;
@Service
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;
    private final UserService userService;

    public PointServiceImpl(PointRepository pointRepository, @Lazy UserService userService) {
        this.pointRepository = pointRepository;
        this.userService = userService;
    }

    @Override
    public Points createPoints(CreatePointRequestDTO createPointRequestDTO) {
        Points points = new Points();
        points.setUsers(userService.getDetailUser(createPointRequestDTO.getUserId()));
        points.setPoint(createPointRequestDTO.getPoint());
        return pointRepository.save(points);
    }

    @Override
    public Points getByUsersId(Long id) {
        return pointRepository.findByUsersId(id);
    }
}
