package com.example.demo.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Pattern(regexp = "^(DEPOSIT|WITHDRAW)$", message = "Needed to be a DEPOSIT or a WITHDRAW type")
    String type;

    @Positive(message = "Must be a positive ammout")
    Double amount;
    LocalDateTime date;
    // amount > 0, amount = deposit | withdraw
}
