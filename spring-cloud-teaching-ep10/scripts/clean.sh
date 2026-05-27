#!/bin/bash
echo "🧹 清理所有服务和数据..."
docker-compose down -v
docker network rm teaching-network 2>/dev/null
echo "✅ 清理完成"
