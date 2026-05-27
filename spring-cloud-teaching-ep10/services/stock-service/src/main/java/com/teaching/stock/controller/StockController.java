package com.teaching.stock.controller;

import com.teaching.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @PostMapping("/deduct")
    public Map<String, Object> deduct(@RequestParam Long productId, @RequestParam Integer quantity) {
        Map<String, Object> result = new HashMap<>();
        stockService.deduct(productId, quantity);
        result.put("code", 200);
        result.put("message", "库存扣减成功");
        return result;
    }
}
