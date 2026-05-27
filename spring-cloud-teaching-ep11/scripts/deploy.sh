#!/bin/bash
set -e

echo "🚀 部署到 Kubernetes..."

kubectl apply -k ../k8s

echo ""
echo "✅ 部署完成！"
echo ""
echo "查看状态："
echo "  kubectl get pods -n teaching-cloud"
echo "  kubectl get svc -n teaching-cloud"
echo "  kubectl get ingress -n teaching-cloud"
echo ""
echo "端口转发测试："
echo "  kubectl port-forward service/order-service 8081:8081 -n teaching-cloud"
