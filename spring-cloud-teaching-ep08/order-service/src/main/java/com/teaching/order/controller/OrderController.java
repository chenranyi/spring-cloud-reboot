package com.teaching.order.controller;

import com.teaching.order.dto.OrderCreateDTO;
import com.teaching.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public Map<String, Object> createOrder(@RequestBody OrderCreateDTO request) {
        Map<String, Object> result = new HashMap<>();
        try {
            orderService.createOrder(request);
            result.put("code", 200);
            result.put("message", "订单创建成功");
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "订单创建失败: " + e.getMessage());
        }
        return result;
    }
}
