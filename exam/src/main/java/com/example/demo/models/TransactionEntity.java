package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Account id cannot be null")
    @ManyToOne
    @JoinColumn(name = "accountId")
    Long accountId;

    @NotNull(message = "Transactions must have a type")
    @Pattern(regexp = "^(DEPOSIT|WITHDRAW)$", message = "Needed to be a DEPOSIT or a WITHDRAW type")
    String type;

    @NotNull(message = "Transactions must have an amount")
    @Positive(message = "Must be a positive amount")
    Double amount;

    private boolean flagged;

    LocalDateTime date;

    // amount > 0, amount = deposit | withdraw
    public TransactionEntity(@Valid String type, @Valid Double amount, @Valid Long accountId, LocalDateTime date){
        this.type = type;
        this.amount = amount;
        this.accountId = accountId;
        this.date = date;
        this.flagged = (amount >= 10000);
    }

    public TransactionEntity(@Valid String type, @Valid Double amount, @Valid Long accountId){
        this(type, amount, accountId, LocalDateTime.now());
    }

    public String getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public boolean getFlagged() {
        return this.flagged;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }
}
