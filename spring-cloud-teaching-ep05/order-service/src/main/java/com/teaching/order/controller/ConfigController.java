package com.teaching.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/config")
@RefreshScope
public class ConfigController {

    @Value("${order.config.name:默认名称}")
    private String serviceName;

    @Value("${order.config.version:1.0}")
    private String version;

    @Value("${order.config.max-batch-size:50}")
    private Integer maxBatchSize;

    @Value("${order.config.enable-feign-log:false}")
    private Boolean enableFeignLog;

    @GetMapping("/info")
    public Map<String, Object> getConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("serviceName", serviceName);
        config.put("version", version);
        config.put("maxBatchSize", maxBatchSize);
        config.put("enableFeignLog", enableFeignLog);
        return config;
    }
}
