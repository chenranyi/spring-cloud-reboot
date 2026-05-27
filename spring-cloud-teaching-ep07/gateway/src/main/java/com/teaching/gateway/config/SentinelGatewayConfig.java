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
        Set<ApiDefinition> apiDefinitions = new HashSet<>();
        
        ApiDefinition orderApi = new ApiDefinition("order-api")
            .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                add(new ApiPathPredicateItem()
                    .setPattern("/api/order/**")
                    .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
            }});
        apiDefinitions.add(orderApi);
        
        GatewayApiDefinitionManager.loadApiDefinitions(apiDefinitions);

        Set<GatewayFlowRule> gatewayRules = new HashSet<>();
        
        GatewayFlowRule orderRule = new GatewayFlowRule("order-api")
            .setCount(10)
            .setIntervalSec(1);
        gatewayRules.add(orderRule);
        
        GatewayRuleManager.loadRules(gatewayRules);
        
        System.out.println("✅ Sentinel 网关限流规则已加载");
    }
}
