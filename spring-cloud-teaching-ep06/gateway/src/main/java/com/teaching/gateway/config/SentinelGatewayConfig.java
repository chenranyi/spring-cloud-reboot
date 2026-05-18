package com.teaching.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import jakarta.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class SentinelGatewayConfig {

    @Bean
    @Order(-1)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    @PostConstruct
    public void initGatewayRules() {
        // 1. 定义 API 分组
        Set<ApiDefinition> apiDefinitions = new HashSet<>();
        
        ApiDefinition orderApi = new ApiDefinition("order-api")
            .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                add(new ApiPathPredicateItem()
                    .setPattern("/api/order/**")
                    .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
            }});
        apiDefinitions.add(orderApi);
        
        ApiDefinition userApi = new ApiDefinition("user-api")
            .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                add(new ApiPathPredicateItem()
                    .setPattern("/api/user/**")
                    .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
            }});
        apiDefinitions.add(userApi);
        
        GatewayApiDefinitionManager.loadApiDefinitions(apiDefinitions);

        // 2. 配置限流规则
        Set<GatewayFlowRule> gatewayRules = new HashSet<>();
        
        // 订单 API：QPS = 5
        GatewayFlowRule orderRule = new GatewayFlowRule("order-api")
            .setCount(5)
            .setIntervalSec(1);
        gatewayRules.add(orderRule);
        
        // 用户 API：QPS = 10
        GatewayFlowRule userRule = new GatewayFlowRule("user-api")
            .setCount(10)
            .setIntervalSec(1);
        gatewayRules.add(userRule);
        
        GatewayRuleManager.loadRules(gatewayRules);
        
        System.out.println("✅ Sentinel 网关限流规则已加载");
        System.out.println("   - order-api: 5 QPS");
        System.out.println("   - user-api: 10 QPS");
    }
}
