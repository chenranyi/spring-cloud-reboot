package com.teaching.order.controller;

import com.teaching.order.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/circuit")
public class CircuitBreakerController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/test")
    public Map<String, Object> testCircuitBreaker() {
        long start = System.currentTimeMillis();
        
        Map<String, Object> user = userFeignClient.getUser(1L);
        
        long cost = System.currentTimeMillis() - start;
        
        Map<String, Object> result = new HashMap<>();
        result.put("userInfo", user);
        result.put("costMs", cost);
        result.put("message", user.containsKey("error") ? "降级生效" : "正常调用");
        
        return result;
    }
}
