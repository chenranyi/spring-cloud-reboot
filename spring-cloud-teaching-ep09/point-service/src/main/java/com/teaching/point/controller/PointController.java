package com.teaching.point.controller;

import com.teaching.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    @PostMapping("/add")
    public Map<String, Object> add(@RequestParam Long userId, @RequestParam Integer points) {
        Map<String, Object> result = new HashMap<>();
        try {
            pointService.add(userId, points);
            result.put("code", 200);
            result.put("message", "积分增加成功");
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
