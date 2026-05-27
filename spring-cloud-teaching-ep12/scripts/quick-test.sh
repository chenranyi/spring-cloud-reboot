#!/bin/bash

# 快速压力测试（使用 ab 工具）
URL=${1:-"http://localhost:8081/api/order/create"}
CONCURRENCY=${2:-100}
REQUESTS=${3:-1000}

echo "🚀 快速压测"
echo "   URL: $URL"
echo "   并发: $CONCURRENCY"
echo "   请求数: $REQUESTS"
echo ""

# 检查 ab 是否安装
if ! command -v ab &> /dev/null; then
    echo "❌ ab 未安装，请安装 apache2-utils 或 httpd-tools"
    exit 1
fi

# 创建临时请求体
cat > /tmp/post_data.json << 'EOF'
{"userId":1,"productId":100,"quantity":2,"amount":198}
