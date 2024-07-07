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
import com.stock_api.stocks.service.TwelveDataService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    public StockService stockService;

    @Autowired
    public TwelveDataService twelveDataService;

    public StockController(StockService stockService){
        this.stockService= stockService;
    }

    @Operation(summary = "Add a new stock")
    @PostMapping("/addStock")
    public Stock saveStock(@RequestBody Stock stock){
        return stockService.saveStock(stock);
    }

    @Operation(summary = "Get all stocks")
    @GetMapping("/getStocks")
    public List<Stock> getAllStocks(){
        return stockService.getAllStocks();
    }

    @Operation(summary = "Get stock by ID")
    @GetMapping("/getStocks/{id}")
    public ResponseEntity<Stock> getStockById(@Parameter(description = "Stock ID") @PathVariable int id){
        return stockService.getStockById(id);
    }

    @Operation(summary = "Delete stock by ID")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Integer id){
        boolean isDeleted= stockService.deleteStockById(id);

        if(isDeleted){
            return new ResponseEntity<>("Stock deleted" , HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Stock not found" , HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update stock by ID")
    @PutMapping("/stocks/{id}")
    public Stock updateStock(@PathVariable int id, @RequestBody Stock stock){
        return stockService.updateStock(id, stock);
    }

    @Operation(summary = "Calculate total stock value")
    @GetMapping("/stocks/totalStockValue")
    public ResponseEntity<Integer> getTotalStockValue(){
        int totalvalue= stockService.calculateTotalStockValue();
        return ResponseEntity.ok(totalvalue);
    }
}
