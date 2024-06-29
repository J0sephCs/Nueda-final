package com.stock_api.stocks.model;

import java.util.Date;

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
@Table(name="StockPriceHistory")
public class StockPriceHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "ticker")
    public String ticker;
    @Column(name = "closingprice")
    public double closingPrice;
    @Column(name = "date")
    public Date date;

    // @ManyToOne
    // @JoinColumn(name = "ticker" , referencedColumnName = "stockTicker", insertable = false, updatable= false)
    // public Stock stock;

    public StockPriceHistory(double closingPrice, Date date, Integer id, String ticker) {
        this.closingPrice = closingPrice;
        this.date = date;
        this.id = id;
        this.ticker = ticker;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    




}
