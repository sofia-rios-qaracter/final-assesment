package com.example.demo.controllers;

import com.example.demo.dto.AccountRequestDTO;
import com.example.demo.models.AccountEntity;
import com.example.demo.models.TransactionEntity;
import com.example.demo.services.AccountService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/accounts")
    public void addAccount(@RequestBody AccountRequestDTO account){
        this.accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public AccountEntity getAccount(@PathVariable Long id){
        return this.accountService.getAccount(id);
    }

    @PostMapping("/{id}/deposit")
    public AccountEntity depositToAccount(@PathVariable Long id, @RequestParam Double amount){
        return this.accountService.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    public AccountEntity withdrawToAccount(@PathVariable Long id, @RequestParam Double amount){
        return this.accountService.withdraw(id, amount);
    }

    @GetMapping("/{id}/transactions")
    public List<TransactionEntity> getTransactionsFrom(@PathVariable Long id){
        return this.accountService.getTransactions(id);
    }

    @GetMapping("/search")
    public List<AccountEntity> getAccountByName(
            @RequestParam @NotNull(message = "Name cannot be null while searching it") String name
    ){
        return this.accountService.getAccountByName(name);
    }
}
