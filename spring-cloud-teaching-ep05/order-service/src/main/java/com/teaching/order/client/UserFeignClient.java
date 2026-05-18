package com.teaching.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "user-service")
public interface UserFeignClient {
    @GetMapping("/api/user/{id}")
    Map<String, Object> getUser(@PathVariable("id") Long id);
}
