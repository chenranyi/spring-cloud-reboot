package com.teaching.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "point-service")
public interface PointFeignClient {
    @PostMapping("/api/point/add")
    String add(@RequestParam("userId") Long userId,
               @RequestParam("points") Integer points);
}
