package com.teaching.order.client;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserFeignClientFallback implements UserFeignClient {

    @Override
    public Map<String, Object> getUser(Long id) {
        Map<String, Object> fallback = new HashMap<>();
        fallback.put("id", id);
        fallback.put("name", "用户服务不可用（降级数据）");
        fallback.put("error", true);
        fallback.put("message", "服务熔断，返回默认数据");
        return fallback;
    }
}
