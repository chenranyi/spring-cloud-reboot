#!/bin/bash

RESULTS_DIR="../jmeter/reports"

echo -e "📊 压测结果汇总表"
echo -e "=================="
echo -e "并发\tQPS\tP99(ms)\t错误率"
echo -e "=================="

for c in 50 100 200 300 400 500; do
    if [ -f "$RESULTS_DIR/report_$c/statistics.json" ]; then
        QPS=$(cat $RESULTS_DIR/report_$c/statistics.json | jq -r '.Total.throughput')
        P99=$(cat $RESULTS_DIR/report_$c/statistics.json | jq -r '.Total.percentiles."99"')
        ERR=$(cat $RESULTS_DIR/report_$c/statistics.json | jq -r '.Total.errorPercentage')
        echo -e "${c}\t${QPS}\t${P99}\t${ERR}%"
    else
        echo -e "${c}\t-\t-\t-"
    fi
done
