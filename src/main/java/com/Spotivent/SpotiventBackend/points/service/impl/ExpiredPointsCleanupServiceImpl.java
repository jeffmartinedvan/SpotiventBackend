package com.Spotivent.SpotiventBackend.points.service.impl;

import com.Spotivent.SpotiventBackend.points.repository.PointRepository;
import com.Spotivent.SpotiventBackend.points.service.ExpiredPointsCleanupService;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ExpiredPointsCleanupServiceImpl implements ExpiredPointsCleanupService {
    private final PointRepository pointRepository;

    public ExpiredPointsCleanupServiceImpl(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")  // Schedule to run every day at midnight
    @Transactional
    @Override
    public void deleteExpiredPoints() {
        Instant currentDate = Instant.now();
        pointRepository.deleteExpiredPoints(currentDate);
    }
}
