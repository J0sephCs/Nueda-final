package com.stock_api.stocks.repository;

import com.stock_api.stocks.model.StockPriceHistory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPriceHistroyRepository extends JpaRepository<StockPriceHistory, Integer> {

    

}
