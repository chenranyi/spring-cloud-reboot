package com.teaching.point.mapper;

import com.teaching.point.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PointRepository extends JpaRepository<Point, Long> {
    Point findByUserId(Long userId);
    
    @Modifying
    @Query("UPDATE Point p SET p.total = p.total + ?2 WHERE p.userId = ?1")
    int addPoints(Long userId, Integer points);
}
