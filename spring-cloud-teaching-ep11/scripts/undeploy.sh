#!/bin/bash
echo "🗑️ 卸载所有资源..."
kubectl delete -k ../k8s --ignore-not-found
echo "✅ 卸载完成"
