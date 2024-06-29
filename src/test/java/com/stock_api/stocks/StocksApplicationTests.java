package com.stock_api.stocks;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stock_api.stocks.model.Stock;
import com.stock_api.stocks.service.StockService;


@SpringBootTest
class StocksApplicationTests {

	@Autowired
	public StockService stockService;


	
	@Test
	void addStockTest() {


		Stock stock= new Stock();
		stock.setStockTicker("jhjhv");
		stock.setCompanyName("Google");
		stock.setStockPrice(120.00);
		stock.setQuantity(1);
		stock.setStockType("Stock");

		stockService.saveStock(stock);
		
	}

}
