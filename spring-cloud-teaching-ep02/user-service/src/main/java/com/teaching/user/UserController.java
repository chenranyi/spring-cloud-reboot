package com.teaching.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/{id}")
    public Map<String, Object> getUser(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", "用户-" + id);
        result.put("email", "user" + id + "@teaching.com");
        result.put("from", "user-service:" + port);
        return result;
    }
}
