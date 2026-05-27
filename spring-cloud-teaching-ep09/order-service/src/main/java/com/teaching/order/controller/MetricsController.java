package com.teaching.order.controller;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/metrics")
@RequiredArgsConstructor
public class MetricsController {

    private final MeterRegistry meterRegistry;

    @GetMapping("/info")
    public Map<String, Object> getMetricsInfo() {
        Map<String, Object> result = new HashMap<>();
        result.put("application", "order-service");
        result.put("metricsEndpoint", "/actuator/prometheus");
        result.put("meters", meterRegistry.getMeters().size());
        return result;
    }
}
