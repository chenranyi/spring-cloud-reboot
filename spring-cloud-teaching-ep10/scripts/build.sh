#!/bin/bash
echo "🔨 构建所有服务..."
cd services
mvn clean package -DskipTests
cd ..
echo "✅ 构建完成"
