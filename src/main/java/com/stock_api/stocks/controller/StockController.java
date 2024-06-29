package com.stock_api.stocks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock_api.stocks.model.Stock;
import com.stock_api.stocks.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    public StockService stockService;

    

    public StockController(StockService stockService){
        this.stockService= stockService;
    }


    // @GetMapping
    // public String getPage(){
    //     return "Yoooo";
    // }

    @PostMapping("/addStock")
    public Stock saveStock(@RequestBody Stock stock){
        return stockService.saveStock(stock);
    }

    @GetMapping("/getStocks")
    public List<Stock> getAllStocks(){
        List<Stock> stockList= stockService.getAllStocks();
        return stockList;
    }

    @GetMapping("/getStocks/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable int id){
        return stockService.getStockById(id);
    }


    // @DeleteMapping("/deleteStock/{ticker}")
    // public void deleteStockById(@PathVariable("ticker") Stock stock){
    //     stockService.deleteStockByTicker(stock.getStockTicker());
    // }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Integer id){
        boolean isDeleted= stockService.deleteStockById(id);


        if(isDeleted){
            return new ResponseEntity<>("Stock deleted" , HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Stock not found" , HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/stocks/{id}")
    public Stock updateStock(@PathVariable int id, @RequestBody Stock stock){
        return stockService.updateStock(id, stock);
    }

    @GetMapping("/stocks/totalStockValue")
    public ResponseEntity<Integer> getTotalStockValue(){
        int totalvalue= stockService.calculateTotalStockValue();
        return ResponseEntity.ok(totalvalue);

    }

    




   

}


