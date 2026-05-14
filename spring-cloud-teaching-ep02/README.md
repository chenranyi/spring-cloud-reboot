# Spring Cloud 微服务实战 · 重修版（第二期）

## 本期目标
- ✅ Nacos 服务注册与发现
- ✅ user-service / order-service 拆分
- ✅ 服务间调用（WebClient + 负载均衡）

## 快速启动

```bash
# 1. 启动 Nacos
docker-compose up -d

# 2. 启动 user-service
cd user-service
mvn spring-boot:run

# 3. 启动 order-service（新终端）
cd order-service
mvn spring-boot:run
