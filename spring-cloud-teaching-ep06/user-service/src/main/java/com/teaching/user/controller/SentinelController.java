package com.teaching.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sentinel")
public class SentinelController {

    @GetMapping("/test")
    @SentinelResource(value = "test-resource", blockHandler = "handleBlock")
    public Map<String, Object> test() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "正常响应");
        result.put("time", System.currentTimeMillis());
        return result;
    }

    public Map<String, Object> handleBlock(BlockException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 429);
        result.put("message", "请求过于频繁，请稍后重试");
        result.put("limit", true);
        return result;
    }
}
