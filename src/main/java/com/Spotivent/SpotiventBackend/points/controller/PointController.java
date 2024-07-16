package com.Spotivent.SpotiventBackend.points.controller;

import com.Spotivent.SpotiventBackend.points.dto.PointResponseDTO;
import com.Spotivent.SpotiventBackend.points.service.PointService;
import com.Spotivent.SpotiventBackend.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/point")
@Validated
public class PointController {
    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Long>> getTotalPointsByUserId(@PathVariable Long id) {
        return Response.success("Get point success", pointService.getTotalPointsByUserId(id));
    }

    @GetMapping("/{id}/all")
    public ResponseEntity<Response<List<PointResponseDTO>>> getAllPointsByUserId(@PathVariable Long id) {
        return Response.success("Get all points success", pointService.getAllPointsByUserId(id));
    }
}
