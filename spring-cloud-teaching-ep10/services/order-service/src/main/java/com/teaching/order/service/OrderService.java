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
        try {
            doCreateOrder(request);
            orderMetrics.recordSuccess();
        } catch (Exception e) {
            orderMetrics.recordFailure();
            throw e;
        }
    }

    private void doCreateOrder(OrderCreateDTO request) {
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setAmount(request.getAmount());
        order.setStatus(0);
        order.setCreateTime(LocalDateTime.now());
        orderRepository.save(order);
        log.info("✅ 订单创建成功，订单ID: {}", order.getId());

        stockFeignClient.deduct(request.getProductId(), request.getQuantity());
        log.info("✅ 库存扣减成功");

        int points = request.getAmount().intValue();
        pointFeignClient.add(request.getUserId(), points);
        log.info("✅ 积分增加成功");
    }
}
