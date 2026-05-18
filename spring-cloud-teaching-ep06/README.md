# Spring Cloud 微服务实战 · 重修版（第六期）

## 本期新增
- ✅ Sentinel 服务容错
- ✅ 限流配置与控制
- ✅ Feign + Sentinel 熔断降级
- ✅ Gateway 网关限流

## 组件说明

| 组件 | 端口 | 说明 |
|------|------|------|
| Nacos | 8848 | 注册中心 + 配置中心 |
| Sentinel | 8858 | 流量控制 + 熔断降级 |
| user-service | 8081 | 用户服务 |
| order-service | 8082 | 订单服务 |
| gateway | 8080 | API 网关 |

## 快速启动

```bash
# 1. 启动 Nacos 和 Sentinel
docker-compose up -d

# 2. 启动用户服务
cd user-service && mvn spring-boot:run

# 3. 启动订单服务
cd order-service && mvn spring-boot:run

# 4. 启动网关
cd gateway && mvn spring-boot:run
