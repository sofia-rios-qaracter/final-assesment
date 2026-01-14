package com.example.demo.exceptions;

public class TooManyWithdrawalsException extends RuntimeException {
    public TooManyWithdrawalsException(String message) {
        super(message);
    }
}
