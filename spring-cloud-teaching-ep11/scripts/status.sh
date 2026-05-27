#!/bin/bash
echo "📊 Kubernetes 集群状态"
echo "========================"
echo ""
echo "Pods:"
kubectl get pods -n teaching-cloud
echo ""
echo "Services:"
kubectl get svc -n teaching-cloud
echo ""
echo "Deployments:"
kubectl get deployments -n teaching-cloud
echo ""
echo "HorizontalPodAutoscalers:"
kubectl get hpa -n teaching-cloud
echo ""
echo "Ingress:"
kubectl get ingress -n teaching-cloud
