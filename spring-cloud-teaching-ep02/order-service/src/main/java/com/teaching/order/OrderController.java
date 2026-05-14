package com.teaching.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${server.port}")
    private String port;

    @GetMapping("/{id}")
    public Mono<Map<String, Object>> getOrder(@PathVariable Long id) {
        // 模拟订单数据
        Map<String, Object> order = new HashMap<>();
        order.put("orderId", id);
        order.put("productName", "Spring Cloud 教学课程");
        order.put("userId", 1L);
        order.put("price", 99.00);
        order.put("from", "order-service:" + port);

        // 调用 user-service 获取用户信息
        return webClientBuilder.build()
                .get()
                .uri("http://user-service/api/user/" + order.get("userId"))
                .retrieve()
                .bodyToMono(Map.class)
                .map(user -> {
                    order.put("userInfo", user);
                    return order;
                });
    }
}
