package com.teaching.order.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class OrderMetrics {
    private final Counter orderCreateCounter;
    private final Counter orderSuccessCounter;
    private final Counter orderFailureCounter;
    private final Timer orderCreateTimer;
    private final AtomicLong pendingOrders;

    public OrderMetrics(MeterRegistry registry) {
        this.orderCreateCounter = Counter.builder("order.create.total")
                .description("订单创建总数").register(registry);
        this.orderSuccessCounter = Counter.builder("order.create.success")
                .description("订单创建成功数").register(registry);
        this.orderFailureCounter = Counter.builder("order.create.failure")
                .description("订单创建失败数").register(registry);
        this.orderCreateTimer = Timer.builder("order.create.duration")
                .description("订单创建耗时").publishPercentiles(0.5, 0.95, 0.99).register(registry);
        this.pendingOrders = registry.gauge("order.pending.count", new AtomicLong(0));
    }

    public void recordCreate() { orderCreateCounter.increment(); }
    public void recordSuccess() { orderSuccessCounter.increment(); }
    public void recordFailure() { orderFailureCounter.increment(); }
    public <T> T recordTimer(Callable<T> callable) throws Exception { return orderCreateTimer.recordCallable(callable); }
    public void setPendingOrders(long count) { pendingOrders.set(count); }
}
