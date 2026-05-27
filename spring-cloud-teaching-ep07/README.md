# Spring Cloud 微服务实战 · 重修版（第七期）

## 本期新增
- ✅ SkyWalking 链路追踪
- ✅ Elasticsearch 存储
- ✅ 完整调用链可视化
- ✅ 慢查询自动识别

## 快速启动

```bash
# 1. 下载 SkyWalking Agent
cd scripts && ./download-agent.sh && cd ..

# 2. 一键启动所有服务
./scripts/start-all.sh

# 3. 测试调用（产生追踪数据）
curl http://localhost:8080/api/order/1

# 4. 慢查询测试（耗时 3 秒）
curl http://localhost:8080/api/user/999
