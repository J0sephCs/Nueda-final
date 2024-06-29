// package com.stock_api.stocks.service;

// import java.text.ParseException;
// import java.util.Arrays;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.ApplicationArguments;
// import org.springframework.boot.ApplicationRunner;
// import org.springframework.stereotype.Component;

// @Component
// public class DataInitializer implements ApplicationRunner {

//     @Autowired
//     public StockPriceHistroyService stockPriceHistroyService;

    
//     public DataInitializer(StockPriceHistroyService stockPriceHistroyService){
//         this.stockPriceHistroyService= stockPriceHistroyService;
//     }

//     @Override
//     public void run(ApplicationArguments args) throws ParseException{
        
//         List<String> tickerList= Arrays.asList(
//             "AAPL", "MSFT", "AMZN", "GOOGL", "FB",
//             "TSLA", "NVDA", "JPM", "V", "MA",
//             "NFLX", "PYPL", "CRM", "BABA", "HD",
//             "DIS", "NKE", "PFE", "MRNA", "VZ",
//             "T", "BA", "XOM", "KO", "PEP",
//             "WMT", "CVS", "MRK", "UNH", "ABBV",
//             "ABNB", "ZM", "SNOW", "SQ", "UBER",
//             "LYFT", "DOCU", "ROKU", "AMD", "SBUX",
//             "CSCO", "ORCL", "IBM", "GM", "F",
//             "GE", "SPOT", "SNAP", "TWTR", "SPY"
//         );

//         stockPriceHistroyService.fetchandStoreStockData(tickerList);
        
//         System.out.println("Stock data imported successfully");
//     }

// }
