package com.teaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class TeachingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeachingApplication.class, args);
        System.out.println("✅ 教学微服务项目启动成功！");
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("project", "Spring Cloud 教学系列");
        status.put("episode", "第一期");
        return status;
    }
}
