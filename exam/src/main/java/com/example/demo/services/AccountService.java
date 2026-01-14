package com.example.demo.services;

import java.util.List;

public class AccountService {

    public AccountEntity createAccount(CreateAccountRequest request){
        return null;
    }

    public AccountEntity getAccount(Long id){
        return null;
    }

    public AccountEntity deposit(Long id, Double amount){
        // Amount > 0
        return null;
    }

    public AccountEntity withdraw(Long id, Double amount){
        // Amount > 0
        // Withdrawals not exceed avaiable balance
        return null;
    }

    public List<TransactionEntity> getTransactions(Long id){
        return null;
    }
}
