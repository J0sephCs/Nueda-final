package com.stock_api.stocks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockPrice {

    @JsonProperty("datetime")
    public String datetime;

    @JsonProperty("symbol")
    public String ticker;

    @JsonProperty("close")
    public Double close_price;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Double getClose_price() {
        return close_price;
    }

    public void setClose_price(Double close_price) {
        this.close_price = close_price;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

}
