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
    public Account getAccount(@PathVariable Long id){
        return null;
    }

    @PostMapping("/{id}/deposit")
    public Account depositToAccount(@PathVariable Long id, @RequestParam Double ammount){
        return null;
    }

    @PostMapping("/{id}/withdraw")
    public Account withdrawToAccount(@PathVariable Long id, @RequestParam Double ammount){
        return null;
    }

    @GetMapping("/{id}/transactions")
    public List<Transaction> getTransactionsFrom(@PathVariable Long id){
        return null;
    }

    @GetMapping("/search")
    public List<Account> getAccountByName(@RequestParam String name){
        return null;
    }
}
