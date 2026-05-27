# Spring Cloud 微服务实战 · 重修版（第八期）

## 本期新增
- ✅ Seata 分布式事务
- ✅ AT 模式自动回滚
- ✅ 下单分布式事务场景
- ✅ undo_log 自动恢复

## 快速启动

```bash
# 1. 初始化数据库
docker-compose up -d mysql
# 等待 MySQL 启动后，执行 sql/init.sql

# 2. 启动其他服务
./scripts/start-all.sh
