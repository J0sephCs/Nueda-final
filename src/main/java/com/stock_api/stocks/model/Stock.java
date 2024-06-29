package com.stock_api.stocks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "stocks")
public class Stock{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "stockTicker")
    public String stockTicker;
    @Column(name = "company")
    public String companyName;
    @Column(name = "price")
    public Double stockPrice;
    @Column(name = "quantity")
    public Integer quantity;
    @Column(name = "stock_type")
    public String stockType;

    public Stock(Integer id, String stockTicker, String companyName, Double stockPrice, Integer quantity, String stockType){
        this.id= id;
        this.stockTicker= stockTicker;
        this.companyName= companyName;
        this.stockPrice= stockPrice;
        this.quantity= quantity;
        this.stockType= stockType;
    }

  
 

    public Integer getId(){
        return id;
    }
    public void setId(int id){
        this.id= id;
    }

    public String getStockTicker() {
        return stockTicker;
    }

    public void setStockTicker(String stockTicker) {
        this.stockTicker = stockTicker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

}
