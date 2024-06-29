package com.stock_api.stocks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock_api.stocks.model.Account;
import com.stock_api.stocks.model.Stock;
import com.stock_api.stocks.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    public AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService= accountService;
    }

    @PostMapping("/saveAccount")
    public Account saveAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }

    @PostMapping("/{id}/addToPortfolio")
    public ResponseEntity<String> addStockToPortfolio(@PathVariable Integer id, @RequestBody Stock stock){
        Account account= accountService.getAccountById(id);

        if(account == null){
            return ResponseEntity.notFound().build();
        }

        if(accountService.canBuyStock(account, stock.getStockPrice(), stock.getQuantity())){
            accountService.addToPortfolio(account, stock, stock.getQuantity());
            accountService.saveAccount(account);
        }
        return ResponseEntity.ok("Stock added to portfolio successfully");
    }

    @DeleteMapping("/{id}/deleteFromPortfolio")
    public ResponseEntity<String> deleteStockFromPortfolio(@PathVariable Integer id, @RequestBody Stock stock){
        Account account= accountService.getAccountById(id);

        if(account != null){
            Stock newStock= new Stock(stock.getId(), stock.getStockTicker(), stock.getCompanyName(), stock.getStockPrice(), stock.getQuantity(), stock.getStockType());
            accountService.deleteFromPortfolio(account, newStock, stock.getQuantity());
            return ResponseEntity.ok("Stock deleted from portfolio");
        }else{
            return ResponseEntity.ok("Stock deletion failed");
        }
    }
}
