#!/bin/bash

echo "🚀 启动所有服务..."

docker-compose up -d

echo "等待 MySQL 启动（15秒）..."
sleep 15

echo "启动 order-service..."
cd order-service && mvn spring-boot:run > ../logs/order-service.log 2>&1 &
cd ..

echo "启动 stock-service..."
cd stock-service && mvn spring-boot:run > ../logs/stock-service.log 2>&1 &
cd ..

echo "启动 point-service..."
cd point-service && mvn spring-boot:run > ../logs/point-service.log 2>&1 &
cd ..

echo ""
echo "✅ 所有服务启动完成"
echo ""
echo "🔗 访问地址："
echo "   Nacos: http://localhost:8848/nacos"
echo "   Seata: http://localhost:7091"
echo ""
echo "🧪 测试命令："
echo "   curl -X POST http://localhost:8081/api/order/create \\"
echo "     -H 'Content-Type: application/json' \\"
echo "     -d '{\"userId\":1,\"productId\":100,\"quantity\":2,\"amount\":198}'"
