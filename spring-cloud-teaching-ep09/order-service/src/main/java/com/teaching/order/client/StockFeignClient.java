package com.teaching.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stock-service")
public interface StockFeignClient {
    @PostMapping("/api/stock/deduct")
    String deduct(@RequestParam("productId") Long productId,
                  @RequestParam("quantity") Integer quantity);
}
