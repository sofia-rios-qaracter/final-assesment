package com.example.demo.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.aspectj.lang.annotation.After;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Transactions must have a type")
    @Pattern(regexp = "^(DEPOSIT|WITHDRAW)$", message = "Needed to be a DEPOSIT or a WITHDRAW type")
    String type;

    @NotNull(message = "Transactions must have an amount")
    @Positive(message = "Must be a positive amount")
    Double amount;

    LocalDateTime date;

    // amount > 0, amount = deposit | withdraw
    public Transaction(@Valid String type, @Valid Double amount, LocalDateTime date){
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(@Valid String type, @Valid Double amount){
        this(type, amount, LocalDateTime.now());
    }

    public String getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
