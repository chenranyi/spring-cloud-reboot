package com.teaching.user.controller;

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

    @Value("${user.config.name:默认名称}")
    private String serviceName;

    @Value("${user.config.version:1.0}")
    private String version;

    @Value("${user.config.enable-cache:false}")
    private Boolean enableCache;

    @Value("${user.config.page-size:10}")
    private Integer pageSize;

    @GetMapping("/info")
    public Map<String, Object> getConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("serviceName", serviceName);
        config.put("version", version);
        config.put("enableCache", enableCache);
        config.put("pageSize", pageSize);
        return config;
    }
}
