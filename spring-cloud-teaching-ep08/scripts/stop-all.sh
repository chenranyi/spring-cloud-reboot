#!/bin/bash

echo "🛑 停止所有服务..."

pkill -f "order-service" 2>/dev/null
pkill -f "stock-service" 2>/dev/null
pkill -f "point-service" 2>/dev/null

docker-compose down

echo "✅ 所有服务已停止"
