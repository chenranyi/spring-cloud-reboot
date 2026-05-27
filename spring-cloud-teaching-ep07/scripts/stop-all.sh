#!/bin/bash

echo "🛑 停止所有服务..."

# 停止 Spring Boot 应用
pkill -f "user-service" 2>/dev/null
pkill -f "order-service" 2>/dev/null
pkill -f "gateway" 2>/dev/null

# 停止 Docker 容器
docker-compose down

echo "✅ 所有服务已停止"
