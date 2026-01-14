package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import javax.annotation.processing.Generated;
import java.util.List;

@Entity
public class Account {
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

    private List<Transaction> transactions;

    public void deposit(@Valid Double amount){
        // amount > 0, add a transaction of type "deposit", update the balance
    }

    public void withdraw(@Valid Double amount){
        // amount > 0
        // must check that sufficient balance exists
        // Adds a transaction of type withdraw
        // update balance
    }

    public List<Transaction> getTransactions(){
        return this.transactions;
    }
}
