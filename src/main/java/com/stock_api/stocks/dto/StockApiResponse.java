package com.stock_api.stocks.dto;

import java.util.List;


public class StockApiResponse {

    public List<StockPrice> values;



    public List<StockPrice> getValues() {
        return values;
    }

    public void setValues(List<StockPrice> values) {
        this.values = values;
    }

 

}
