package com.stock_api.stocks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stock_api.stocks.service.TwelveDataService;

@RestController
@RequestMapping("/twelve")
public class TwelveDataServiceController{

    @Autowired
    public TwelveDataService twelveDataService;

    public TwelveDataServiceController(TwelveDataService twelveDataService) {
        this.twelveDataService = twelveDataService;
    }


    @GetMapping("/getPastPrice")
    public Double getPastPrice(@RequestParam String stockSymbol) {

        return twelveDataService.fetchLatestPrice(stockSymbol);
    }
  


}
