package com.example.demo.controllers;

import com.example.demo.models.Account;
import com.example.demo.models.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/accounts")
public class AccountController {

    @PostMapping("/api/accounts")
    public void addAccount(@RequestBody Account account){

    }

    @GetMapping("/{id}")
    public AccountEntity getAccount(@PathVariable Long id){
        return null;
    }

    @PostMapping("/{id}/deposit")
    public AccountEntity depositToAccount(@PathVariable Long id, @RequestParam Double ammount){
        return null;
    }

    @PostMapping("/{id}/withdraw")
    public AccountEntity withdrawToAccount(@PathVariable Long id, @RequestParam Double ammount){
        return null;
    }

    @GetMapping("/{id}/transactions")
    public List<TransactionEntity> getTransactionsFrom(@PathVariable Long id){
        return null;
    }
}
