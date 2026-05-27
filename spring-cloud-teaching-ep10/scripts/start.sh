#!/bin/bash
echo "🚀 启动所有服务..."
docker-compose up -d
sleep 30
echo ""
echo "✅ 所有服务启动完成"
echo ""
echo "🔗 访问地址："
echo "   Nacos:      http://localhost:8848/nacos"
echo "   Sentinel:   http://localhost:8858"
echo "   Seata:      http://localhost:7091"
echo "   Prometheus: http://localhost:9090"
echo "   Grafana:    http://localhost:3000 (admin/admin)"
echo "   SkyWalking: http://localhost:8088"
echo ""
echo "🧪 测试: curl -X POST http://localhost:8081/api/order/create -H 'Content-Type: application/json' -d '{\"userId\":1,\"productId\":100,\"quantity\":2,\"amount\":198}'"
