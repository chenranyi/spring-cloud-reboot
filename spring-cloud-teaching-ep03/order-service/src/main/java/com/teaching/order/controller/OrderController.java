package com.teaching.order.controller;
import com.teaching.order.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private UserFeignClient userFeignClient;
    @Value("${server.port}")
    private String port;
    @GetMapping("/{id}")
    public Map<String, Object> getOrder(@PathVariable Long id) {
        Map<String, Object> order = new HashMap<>();
        order.put("orderId", id);
        order.put("productName", "Spring Cloud 教学课程");
        order.put("userId", 1L);
        order.put("price", 99.00);
        order.put("from", "order-service:" + port);
        Map<String, Object> userInfo = userFeignClient.getUser(1L);
        order.put("userInfo", userInfo);
        return order;
    }
}
