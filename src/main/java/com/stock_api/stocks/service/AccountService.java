package com.stock_api.stocks.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock_api.stocks.model.Account;
import com.stock_api.stocks.model.Stock;
import com.stock_api.stocks.repository.AccountRepository;


@Service
public class AccountService {


    @Autowired
    public AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository= accountRepository;
    }


    //save account
    public Account saveAccount(Account account){
        return accountRepository.save(account);
    }



    public boolean canBuyStock(Account account, Double price, int quantity){
        double totalCost= price*quantity;
        double balance= account.getAccountBalance();

        return  balance >= totalCost;
    }

    public Account getAccountById(int id){
        return accountRepository.findById(id).orElse(null);
    }


    // add stock to portfolio
    public void addToPortfolio(Account account, Stock stock, int quantity){
        for(Stock stockVal : account.getPortfolio()){
            if(stockVal.getStockTicker().equals(stock.getStockTicker())){
                int newQuantity= stockVal.getQuantity()+quantity;
                stockVal.setQuantity(newQuantity);
                return;
            }
        }

        Stock newStock= new Stock(stock.getId(), stock.getStockTicker(), stock.getCompanyName(),  stock.getStockPrice(), quantity, stock.getStockType());
        account.getPortfolio().add(newStock);
        accountRepository.save(account);
    }



    
    // delete stock from portfolio
    public void deleteFromPortfolio(Account account, Stock stock, int quantity){
        Iterator<Stock> iterator= account.getPortfolio().iterator();
        while(iterator.hasNext()){
            Stock newStock= iterator.next();
            if(newStock.getStockTicker().equals(stock.getStockTicker())){
                int newQuantity= newStock.getQuantity() - quantity;

                if(newQuantity <=0){
                    iterator.remove();
                }else{
                    newStock.setQuantity(newQuantity);
                }
                return;
            }
        }
    }



  
}
