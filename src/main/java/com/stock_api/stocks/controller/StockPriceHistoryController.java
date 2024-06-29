package com.stock_api.stocks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock_api.stocks.service.StockPriceHistroyService;

@RestController
@RequestMapping("/api/stocks")
public class StockPriceHistoryController{

    @Autowired
    public StockPriceHistroyService stockPriceHistroyService;

    public StockPriceHistoryController(StockPriceHistroyService stockPriceHistroyService) {
        this.stockPriceHistroyService = stockPriceHistroyService;
    }

  


}
