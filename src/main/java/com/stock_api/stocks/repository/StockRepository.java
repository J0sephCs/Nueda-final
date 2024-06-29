package com.stock_api.stocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.stock_api.stocks.model.Stock;

@Repository
public interface StockRepository extends JpaRepository <Stock, Integer>{



    public ResponseEntity<Stock> getStockById(int stockid);










}
