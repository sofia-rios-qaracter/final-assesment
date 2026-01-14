package com.example.demo.models;

import java.time.LocalDateTime;

public class Transaction {
    Long id;
    String type;
    Double amount;
    LocalDateTime date;
    // amount > 0, amount = deposit | withdraw
}
