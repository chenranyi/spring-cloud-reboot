# Spring Cloud 微服务实战 · 重修版（第五期）

## 本期新增
- ✅ Nacos 配置中心集成
- ✅ 动态配置刷新（@RefreshScope）
- ✅ 多环境配置支持
- ✅ 配置优先级演示

## Nacos 配置准备

### user-service-dev.yml
```yaml
user:
  config:
    name: "教学用户服务"
    version: "1.0.0"
    enable-cache: true
    page-size: 10
