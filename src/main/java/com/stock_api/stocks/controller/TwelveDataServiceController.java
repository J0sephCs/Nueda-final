package com.stock_api.stocks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stock_api.stocks.service.TwelveDataService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/twelve")
public class TwelveDataServiceController {

    @Autowired
    public TwelveDataService twelveDataService;

    public TwelveDataServiceController(TwelveDataService twelveDataService) {
        this.twelveDataService = twelveDataService;
    }

    @Operation(summary = "Get past price of a stock")
    @GetMapping("/getPastPrice")
    public Double getPastPrice(@Parameter(description = "Stock symbol") @RequestParam String stockSymbol) {
        return twelveDataService.fetchLatestPrice(stockSymbol);
    }
}
