#!/bin/bash

echo "🔌 启动端口转发..."
echo "  order-service: http://localhost:8081"
echo "  nacos: http://localhost:8848"
echo "  seata: http://localhost:7091"

kubectl port-forward service/order-service 8081:8081 -n teaching-cloud &
kubectl port-forward service/nacos 8848:8848 -n teaching-cloud &
kubectl port-forward service/seata-server 7091:7091 -n teaching-cloud &

echo ""
echo "按 Ctrl+C 停止"
wait
