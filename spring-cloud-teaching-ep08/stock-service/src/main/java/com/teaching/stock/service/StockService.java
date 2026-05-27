package com.teaching.stock.service;

import com.teaching.stock.entity.Stock;
import com.teaching.stock.mapper.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {

    private final StockRepository stockRepository;

    public void deduct(Long productId, Integer quantity) {
        log.info("扣减库存: productId={}, quantity={}", productId, quantity);

        Stock stock = stockRepository.findByProductId(productId);
        if (stock == null) {
            throw new RuntimeException("商品不存在: " + productId);
        }

        int result = stockRepository.deductStock(productId, quantity);
        if (result == 0) {
            throw new RuntimeException("库存不足: productId=" + productId + ", 当前可用=" + stock.getAvailable());
        }

        log.info("库存扣减成功");
    }
}
