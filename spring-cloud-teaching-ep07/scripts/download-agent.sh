#!/bin/bash

AGENT_VERSION="9.7.0"
AGENT_DIR="skywalking-agent"

echo "📦 下载 SkyWalking Java Agent ${AGENT_VERSION}..."

if [ -d "$AGENT_DIR" ] && [ -f "$AGENT_DIR/skywalking-agent.jar" ]; then
    echo "✅ Agent 已存在，跳过下载"
    exit 0
fi

wget -q --show-progress \
    "https://archive.apache.org/dist/skywalking/java-agent/${AGENT_VERSION}/apache-skywalking-java-agent-${AGENT_VERSION}.tgz" \
    -O /tmp/skywalking-agent.tgz

if [ $? -eq 0 ]; then
    tar -xzf /tmp/skywalking-agent.tgz -C .
    rm /tmp/skywalking-agent.tgz
    echo "✅ SkyWalking Agent 下载完成: $(pwd)/${AGENT_DIR}"
    echo "📁 Agent 目录: ${AGENT_DIR}"
    echo "📄 配置文件: ${AGENT_DIR}/config/agent.config"
else
    echo "❌ 下载失败，请检查网络"
    echo "💡 手动下载地址: https://archive.apache.org/dist/skywalking/java-agent/${AGENT_VERSION}/"
    exit 1
fi
