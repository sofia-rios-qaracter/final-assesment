package com.example.demo.services;

import com.example.demo.dto.AccountRequestDTO;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.models.AccountEntity;
import com.example.demo.models.TransactionEntity;
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
    public AccountEntity createAccount(AccountRequestDTO account){
        return this.accountRepository.save(new AccountEntity(account.getOwnerName(), account.getIban(),account.getBalance()));
    }

    public AccountEntity getAccount(Long id){
        return this.accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    public AccountEntity deposit(Long id, Double amount){
        AccountEntity account = this.getAccount(id);
        account.deposit(amount);

        return account;
    }

    public AccountEntity withdraw(Long id, Double amount){
        // Amount > 0
        // Withdrawals not exceed avaiable balance
        AccountEntity account = this.getAccount(id);
        account.withdraw(amount);

        return account;
    }

    public List<TransactionEntity> getTransactions(Long id){
        return getAccount(id).getTransactions();
    }
}
