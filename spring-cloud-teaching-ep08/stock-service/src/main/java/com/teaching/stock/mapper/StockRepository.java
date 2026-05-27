package com.teaching.stock.mapper;

import com.teaching.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByProductId(Long productId);
    
    @Modifying
    @Transactional
    @Query("UPDATE Stock s SET s.used = s.used + ?2, s.available = s.available - ?2 WHERE s.productId = ?1 AND s.available >= ?2")
    int deductStock(Long productId, Integer quantity);
}
