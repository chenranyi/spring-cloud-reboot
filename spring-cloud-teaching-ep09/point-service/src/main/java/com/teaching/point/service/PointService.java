package com.teaching.point.service;

import com.teaching.point.mapper.PointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointService {

    private final PointRepository pointRepository;

    public void add(Long userId, Integer points) {
        log.info("增加积分: userId={}, points={}", userId, points);

        int result = pointRepository.addPoints(userId, points);
        if (result == 0) {
            throw new RuntimeException("用户不存在: " + userId);
        }

        log.info("积分增加成功");
    }
}
