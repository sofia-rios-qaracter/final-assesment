package com.example.demo.entities;

public class AccountRequestDTO {
    private String ownerName;
    private String iban;
    private Double balance;

    public AccountRequestDTO(String ownerName, String iban, Double balance){
        this.ownerName = ownerName;
        this.iban = iban;
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getIban() {
        return iban;
    }
}
