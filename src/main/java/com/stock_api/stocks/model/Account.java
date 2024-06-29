package com.stock_api.stocks.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name= "accounts")
public class Account {

    @Id
    @Column(name="account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer accountid;

    @OneToMany
    @Column(name="portfolio")
    public List<Stock> portfolio;
    @Column(name="balance")
    public Double accountBalance;
    @Column(name="username")
    public String username;
//     @Column(name="porttotal")
//    public Double portfolioTotal;


    
 

  
    // public Account(Double accountBalance, Integer accountid, Double portfolioTotal, String username) {
    //     this.accountBalance = accountBalance;
    //     this.accountid = accountid;
    //     this.portfolioTotal = portfolioTotal;
    //     this.username = username;
    // }

  

    public Account(Double accountBalance, Integer accountid, List<Stock> portfolio, String username) {
        this.accountBalance = accountBalance;
        this.accountid = accountid;
        this.portfolio = portfolio;
        //this.portfolioTotal = portfolioTotal;
        this.username = username;
    }

    public Integer getAccountId(){
        return accountid;
    }
    public void setAccountId(Integer accountid){
        this.accountid= accountid;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username= username;
    }

    public Double getAccountBalance(){
        return accountBalance;
    }
    public void setAccountBalance(Double accountBalance){
         this.accountBalance= accountBalance;
    }


    // public Double getPortfolioTotal(){
    //     return portfolioTotal;
    // }
    // public void setPortfolioTotal(Double total){
    //     this.portfolioTotal= total;
    // }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }


   

}

  /*
    
    create function to cheeck if user has enough cash to buy stock
    
    create functions to add/delete stocks

    create function to calculate returns fro the past 5 years 

    create function for sharp ratio

    create a function to find the most optoimal way to allocate your balance (find an algo) risk parity calulation
    maximise returns minimise risk
    
    */ 
