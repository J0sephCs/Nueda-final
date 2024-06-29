package com.stock_api.stocks.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stock_api.stocks.dto.StockApiResponse;
import com.stock_api.stocks.dto.StockPrice;
import com.stock_api.stocks.model.StockPriceHistory;
import com.stock_api.stocks.repository.StockPriceHistroyRepository;


@Service
public class StockPriceHistroyService {

    @Autowired
    public StockPriceHistroyRepository stockPriceHistroyRepository;
    @Autowired
    public RestTemplate restTemplate;
    private final String apiKey= "761f6465c03745a9870347bae366123d";

    
    public StockPriceHistroyService(RestTemplate restTemplate, StockPriceHistroyRepository stockPriceHistroyRepository) {
        this.restTemplate = restTemplate;
        this.stockPriceHistroyRepository = stockPriceHistroyRepository;
    }

    public void fetchandStoreStockData(List<String> tickers) throws ParseException{
        String baseUrl= "https://api.twelvedata.com/time_series?symbol=%s&interval=1day&apikey=%s";

       // String baseUrl="https://api.twelvedata.com/eod?symbol=AAPL&apikey=your_api_key";



       SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");

        for(String ticker : tickers){
            String url= String.format(baseUrl,ticker,apiKey);
            StockApiResponse response;
            response = restTemplate.getForObject(url, StockApiResponse.class);

            if(response != null && response.getValues() != null){
                List<StockPrice> values= response.getValues();
                for(StockPrice stockPrice : values){
                    StockPriceHistory history= new StockPriceHistory();
                    history.setTicker(ticker);
                    history.setClosingPrice(stockPrice.getClose_price());
                    history.setDate(dateFormat.parse(stockPrice.getDatetime()));
                    stockPriceHistroyRepository.save(history);
                }

            }
        }

    }
}
