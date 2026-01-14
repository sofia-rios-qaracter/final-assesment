package com.example.demo.models;

import java.util.List;

public class Account {
    Long id;
    String ownerName;
    String iban;
    Double balance;
    List<Transaction> transactions;

    public void deposit(Double amount){
        // amount > 0, add a transaction of type "deposit", update the balance
    }

    public void withdraw(Double amount){
        // amount > 0
        // must check that sufficient balance exists
        // Adds a transaction of type withdraw
        // update balance
    }

    public List<Transaction> getTransactions(){
        return this.transactions;
    }
}
