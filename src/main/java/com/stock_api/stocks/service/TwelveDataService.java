package com.stock_api.stocks.service;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Request;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;




@Service
public class TwelveDataService {

    
    private final AsyncHttpClient asyncHttpClient;

   @Value("${twelve.data.api.key}")
    private String apiKey;

    public TwelveDataService() {
        this.asyncHttpClient = new DefaultAsyncHttpClient();
    }

    public Double fetchLatestPrice(String symbol) {
        String url = "https://api.twelvedata.com/time_series";
        Request request = asyncHttpClient.prepareGet(url)
                .addQueryParam("apikey", apiKey)
                .addQueryParam("interval", "1day")
                .addQueryParam("symbol", symbol)
                .addQueryParam("outputsize", "1")
                .addQueryParam("format", "CSV")
                .build();

        try {
            Response response = asyncHttpClient.executeRequest(request).get();
            if (response.getStatusCode() == 200) {
                String csvData = response.getResponseBody();
                String[] lines = csvData.split("\\r?\\n");
                if (lines.length > 1) {
                    String[] fields = lines[1].split(";");
                    if (fields.length >= 5) {
                        return Double.parseDouble(fields[4]); // Close price is at index 4 in your CSV
                    }
                }
                throw new RuntimeException("No data found for symbol: " + symbol);
            } else {
                throw new RuntimeException("Failed to fetch latest price from Twelve Data API. Status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch latest price from Twelve Data API", e);
        }
    }
}
