# Spring Cloud 微服务实战 · 重修版（第十二期）

## 本期内容
- ✅ JMeter 压测脚本
- ✅ 阶梯加压测试
- ✅ JVM 调优配置
- ✅ 数据库连接池调优
- ✅ Sentinel 限流配置
- ✅ Prometheus 告警规则
- ✅ 压测结果汇总

## 快速使用

```bash
# 1. 安装 JMeter
export JMETER_HOME=/path/to/apache-jmeter-5.6.3

# 2. 执行压测
./scripts/bench-test.sh

# 3. 快速压测（使用 ab）
./scripts/quick-test.sh http://localhost:8081/api/order/create 100 1000

# 4. 实时监控
./scripts/monitor.sh

# 5. 汇总结果
./scripts/summary-results.sh
