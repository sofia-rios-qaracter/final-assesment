package com.example.demo.services;

import com.example.demo.dto.AccountRequestDTO;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.models.Account;
import com.example.demo.models.Transaction;
import com.example.demo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    //Eliminacion temporal de entity en account y transaction
    // CreateAccountRequest request tiene que ir como parametro modificado
    public Account createAccount(AccountRequestDTO account){
        return this.accountRepository.save(new Account(account.getOwnerName(), account.getIban(),account.getBalance()));
    }

    public Account getAccount(Long id){
        return this.accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    public Account deposit(Long id, Double amount){
        Account account = this.getAccount(id);
        account.deposit(amount);

        return account;
    }

    public Account withdraw(Long id, Double amount){
        // Amount > 0
        // Withdrawals not exceed avaiable balance
        Account account = this.getAccount(id);
        account.withdraw(amount);

        return account;
    }

    public List<Transaction> getTransactions(Long id){
        return getAccount(id).getTransactions();
    }
}
