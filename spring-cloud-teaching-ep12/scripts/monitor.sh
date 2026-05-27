#!/bin/bash

echo "📊 服务监控（每 5 秒刷新）"
echo "=========================================="

while true; do
    clear
    echo "📊 服务监控 - $(date '+%Y-%m-%d %H:%M:%S')"
    echo "=========================================="
    echo ""
    
    # 服务健康检查
    echo "🔍 服务状态："
    for svc in order-service stock-service point-service gateway; do
        STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:8081/actuator/health 2>/dev/null)
        if [ "$STATUS" = "200" ]; then
            echo "   ✅ $svc: 正常"
        else
            echo "   ❌ $svc: 异常"
        fi
    done
    
    echo ""
    
    # 获取 QPS（需要 Prometheus）
    if command -v curl &> /dev/null; then
        QPS=$(curl -s "http://localhost:9090/api/v1/query?query=sum(rate(http_server_requests_seconds_count[1m]))" 2>/dev/null | jq -r '.data.result[0].value[1]' 2>/dev/null)
        if [ -n "$QPS" ] && [ "$QPS" != "null" ]; then
            echo "📈 实时 QPS: $QPS"
        fi
    fi
    
    sleep 5
done
