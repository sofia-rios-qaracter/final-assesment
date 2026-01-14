package com.example.demo.models;

import com.example.demo.exceptions.InsufficientBalanceException;
import com.example.demo.exceptions.InvalidAmountException;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Must have an owner name")
    @Pattern(regexp = ".*", message = "The owner name can't be empty")
    private String ownerName;

    @NotNull(message = "Must have an IBAN associated")
    @Pattern(regexp = "^ES.*", message = "IBAN must start with ES")
    private String iban;

    @Positive(message = "Balance must be a positive")
    private Double balance;

    @OneToMany
    private List<TransactionEntity> transactions;

    public AccountEntity(@Valid String ownerName, @Valid String iban, @Valid Double balance){
        this.ownerName = ownerName;
        this.iban = iban;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public void deposit(@Valid Double amount){
        // amount > 0, add a transaction of type "deposit", update the balance
        if(amount <= 0) throw new InvalidAmountException("Deposited amount must be positive");

        this.transactions.add(new TransactionEntity("DEPOSIT", amount, this.id));
        this.balance += amount;
    }

    public void withdraw(@Valid Double amount){
        // amount > 0
        // must check that sufficient balance exists
        // Adds a transaction of type withdraw
        // update balance
        if(amount <= 0) throw new InvalidAmountException("Withdraw amount must be positive");
        if(this.balance < amount) throw new InsufficientBalanceException("You don't have enough balance in your account");

        this.transactions.add(new TransactionEntity("WITHDRAW", amount, this.id));
        this.balance -= amount;
    }

    public List<TransactionEntity> getTransactions(){
        return this.transactions;
    }

    public Long getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

    public String getIban() {
        return iban;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(@Valid String ownerName) {
        this.ownerName = ownerName;
    }
}
