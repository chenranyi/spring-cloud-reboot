#!/bin/bash

echo "🚀 启动所有服务..."

# 启动 Docker 服务
echo "1. 启动 Nacos + Sentinel + SkyWalking..."
docker-compose up -d

# 等待服务启动
echo "   等待服务启动（15秒）..."
sleep 15

# 启动 user-service
echo "2. 启动 user-service..."
cd user-service
mvn spring-boot:run > ../logs/user-service.log 2>&1 &
cd ..

# 启动 order-service
echo "3. 启动 order-service..."
cd order-service
mvn spring-boot:run > ../logs/order-service.log 2>&1 &
cd ..

# 启动 gateway
echo "4. 启动 gateway..."
cd gateway
mvn spring-boot:run > ../logs/gateway.log 2>&1 &
cd ..

echo ""
echo "=========================================="
echo "✅ 所有服务启动完成！"
echo "=========================================="
echo ""
echo "🔗 访问地址："
echo "   Nacos:    http://localhost:8848/nacos"
echo "   Sentinel: http://localhost:8858"
echo "   SkyWalking UI: http://localhost:8088"
echo ""
echo "🧪 测试命令："
echo "   curl http://localhost:8080/api/order/1"
echo "   curl http://localhost:8080/api/user/999  # 慢查询测试"
echo ""
echo "📊 查看日志："
echo "   tail -f logs/user-service.log"
echo "   tail -f logs/order-service.log"
echo "   tail -f logs/gateway.log"
echo "=========================================="
