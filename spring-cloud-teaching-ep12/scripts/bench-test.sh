#!/bin/bash

# 颜色输出
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

JMETER_HOME=${JMETER_HOME:-/opt/jmeter}
JMETER="${JMETER_HOME}/bin/jmeter"

# 检查 JMeter 是否存在
if [ ! -f "$JMETER" ]; then
    echo -e "${RED}❌ JMeter 未找到，请设置 JMETER_HOME 环境变量${NC}"
    echo "安装方法:"
    echo "  wget https://archive.apache.org/dist/jmeter/binaries/apache-jmeter-5.6.3.tgz"
    echo "  tar -zxvf apache-jmeter-5.6.3.tgz"
    echo "  export JMETER_HOME=\$(pwd)/apache-jmeter-5.6.3"
    exit 1
fi

# 压测参数
CONCURRENCY=(50 100 200 300 400 500)
DURATION=60
RAMPUP=10
LOOP=50

RESULTS_DIR="../jmeter/reports"
DATA_DIR="../jmeter/data"

mkdir -p $RESULTS_DIR

echo -e "${GREEN}==========================================${NC}"
echo -e "${GREEN}🚀 开始压测${NC}"
echo -e "${GREEN}==========================================${NC}"

for c in "${CONCURRENCY[@]}"; do
    echo ""
    echo -e "${YELLOW}📊 压测并发: $c${NC}"
    echo -e "${YELLOW}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
    
    # 执行压测
    $JMETER -n \
        -t ../jmeter/plans/order-basic-test.jmx \
        -Jthreads=$c \
        -Jrampup=$RAMPUP \
        -Jloop=$LOOP \
        -l $RESULTS_DIR/result_${c}.jtl \
        -e -o $RESULTS_DIR/report_${c}
    
    # 提取关键指标
    echo ""
    echo -e "${GREEN}📈 压测结果 (并发=$c):${NC}"
    
    # 提取 QPS
    QPS=$(cat $RESULTS_DIR/report_${c}/statistics.json 2>/dev/null | jq '.Total.throughput' 2>/dev/null)
    if [ -n "$QPS" ]; then
        echo "   QPS: $QPS"
    fi
    
    # 提取响应时间
    P99=$(cat $RESULTS_DIR/report_${c}/statistics.json 2>/dev/null | jq '.Total.percentiles."99"' 2>/dev/null)
    if [ -n "$P99" ]; then
        echo "   P99: ${P99}ms"
    fi
    
    # 提取错误率
    ERR=$(cat $RESULTS_DIR/report_${c}/statistics.json 2>/dev/null | jq '.Total.errorPercentage' 2>/dev/null)
    if [ -n "$ERR" ]; then
        echo "   错误率: ${ERR}%"
    fi
    
    sleep 10
done

echo ""
echo -e "${GREEN}==========================================${NC}"
echo -e "${GREEN}✅ 压测完成！${NC}"
echo -e "${GREEN}==========================================${NC}"
echo ""
echo "📁 报告位置: $RESULTS_DIR"
echo "📊 查看报告: open $RESULTS_DIR/report_*/index.html"
