package com.teaching.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/{id}")
    public Map<String, Object> getUser(@PathVariable Long id) {
        // 模拟慢查询
        if (id == 999) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return Map.of(
            "id", id,
            "name", "用户-" + id,
            "email", "user" + id + "@teaching.com",
            "from", "user-service:" + port
        );
    }
}
