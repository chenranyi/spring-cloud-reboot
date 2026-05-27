package com.teaching.order.service;

import com.teaching.order.client.PointFeignClient;
import com.teaching.order.client.StockFeignClient;
import com.teaching.order.dto.OrderCreateDTO;
import com.teaching.order.entity.Order;
import com.teaching.order.mapper.OrderRepository;
import com.teaching.order.metrics.OrderMetrics;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final StockFeignClient stockFeignClient;
    private final PointFeignClient pointFeignClient;
    private final OrderMetrics orderMetrics;

    @GlobalTransactional(name = "create-order", rollbackFor = Exception.class)
    public void createOrder(OrderCreateDTO request) {
        orderMetrics.recordCreate();
        long startTime = System.currentTimeMillis();

        try {
            doCreateOrder(request);
            orderMetrics.recordSuccess();
        } catch (Exception e) {
            orderMetrics.recordFailure();
            throw e;
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            log.info("订单创建耗时: {}ms", duration);
        }
    }

    private void doCreateOrder(OrderCreateDTO request) {
        log.info("============= 开始创建订单 =============");

        // 1. 创建订单（本地事务）
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setAmount(request.getAmount());
        order.setStatus(0);
        order.setCreateTime(LocalDateTime.now());
        order = orderRepository.save(order);
        log.info("✅ 订单创建成功，订单ID: {}", order.getId());

        // 2. 扣减库存（远程调用）
        log.info("调用库存服务：扣减库存");
        stockFeignClient.deduct(request.getProductId(), request.getQuantity());
        log.info("✅ 库存扣减成功");

        // 3. 增加积分（远程调用）
        log.info("调用积分服务：增加积分");
        int points = request.getAmount().intValue();
        pointFeignClient.add(request.getUserId(), points);
        log.info("✅ 积分增加成功");

        log.info("============= 订单创建完成 =============");
    }
}
