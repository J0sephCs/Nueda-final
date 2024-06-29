package com.stock_api.stocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock_api.stocks.model.Account;


@Repository
public interface AccountRepository extends JpaRepository <Account, Integer> {



}
