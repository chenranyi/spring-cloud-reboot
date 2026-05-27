#!/bin/bash

REGISTRY=${REGISTRY:-teaching}
TAG=${TAG:-latest}

services=("order-service" "stock-service" "point-service")

for service in "${services[@]}"; do
    echo "🔨 Building $service..."
    cd ../services/$service
    mvn clean package -DskipTests -q
    docker build -t ${REGISTRY}/${service}:${TAG} -f Dockerfile .
    cd -
done

echo "✅ 镜像构建完成"
echo ""
echo "如需推送到仓库："
echo "  docker push ${REGISTRY}/order-service:${TAG}"
echo "  docker push ${REGISTRY}/stock-service:${TAG}"
echo "  docker push ${REGISTRY}/point-service:${TAG}"
