package com.stock_api.stocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stock_api.stocks.model.Stock;
import com.stock_api.stocks.repository.StockRepository;



@Service
public class StockService {
    

    @Autowired
    public StockRepository stockRepository;


    public StockService(StockRepository stockRepository){
        this.stockRepository= stockRepository;
    }



    //save
    public Stock saveStock(Stock stock){
         return stockRepository.save(stock);
    }
    

    //delete by ID
    public boolean deleteStockById(Integer id){
        if(stockRepository.existsById(id)){
            stockRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    // delete all
    public void deleteStock(){
        stockRepository.deleteAll();
    }

    //list all stocks
    public List<Stock> getAllStocks(){
       return stockRepository.findAll();
       
    }


    // list a stock by ID
    public ResponseEntity<Stock> getStockById(int stockid){
        return stockRepository.getStockById(stockid);
    }


    // update Stock
    public Stock updateStock(int id, Stock stock){
        Stock existingStock= stockRepository.findById(id).get();

        existingStock.setStockTicker(stock.getStockTicker());
        existingStock.setCompanyName(stock.getCompanyName());
        existingStock.setQuantity(stock.getQuantity());
        existingStock.setStockPrice(stock.getStockPrice());
        existingStock.setStockType(stock.getStockType());
        
        return stockRepository.save(existingStock);
    }

    //calculate stock total
    public int calculateTotalStockValue(){
        List<Stock> stockList = stockRepository.findAll();
        int totalStockValue= 0;
        for(Stock stockVal : stockList){
            totalStockValue+= stockVal.getQuantity()*stockVal.getStockPrice();
        }
        return totalStockValue;
    }

    //calculate 5% increase change 
    public double calculatePercentChange(){
        List<Stock> stockList= stockRepository.findAll();
        double finalPrice= 0.0;

        for(Stock stockVal : stockList){
            double originalValue= stockVal.getStockPrice()*stockVal.getQuantity();
            double increasedAmount= 0.05*originalValue;
            double increasedValue= originalValue + increasedAmount;
            finalPrice+= increasedValue;
        }
        return finalPrice;
    }







}
