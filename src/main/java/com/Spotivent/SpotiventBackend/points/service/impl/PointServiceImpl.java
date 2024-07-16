package com.Spotivent.SpotiventBackend.points.service.impl;

import com.Spotivent.SpotiventBackend.points.dto.CreatePointRequestDTO;
import com.Spotivent.SpotiventBackend.points.dto.PointResponseDTO;
import com.Spotivent.SpotiventBackend.points.entity.Points;
import com.Spotivent.SpotiventBackend.points.repository.PointRepository;
import com.Spotivent.SpotiventBackend.points.service.PointService;
import com.Spotivent.SpotiventBackend.users.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Long getTotalPointsByUserId(Long userId) {
        List<Points> pointsList = pointRepository.findAllByUsersId(userId);
        return pointsList.stream().mapToLong(Points::getPoint).sum();
    }

    public PointResponseDTO mapToPointResponseDTO(Points points) {
        PointResponseDTO responseDTO = new PointResponseDTO();
        responseDTO.setId(points.getId());
        responseDTO.setUserId(points.getUsers().getId());
        responseDTO.setPoint(points.getPoint());
        return responseDTO;
    }
}
