package com.example.demo.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super("The account with the id: "+id+" was not found");
    }
}
