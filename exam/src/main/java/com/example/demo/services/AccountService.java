package com.example.demo.services;

import com.example.demo.dto.AccountRequestDTO;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.exceptions.TooManyWithdrawalsException;
import com.example.demo.models.AccountEntity;
import com.example.demo.models.TransactionEntity;
import com.example.demo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
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

        List<TransactionEntity> transactions = account.getTransactions();
        if(
                transactions.stream()
                        .filter(transactionEntity ->
                                LocalDateTime.now().minusMinutes(10).isBefore(transactionEntity.getDate())
                        ).toList().size() > 3
        ) throw new TooManyWithdrawalsException("There has been to many withdrawals");

        account.withdraw(amount);

        return account;
    }

    public List<TransactionEntity> getTransactions(Long id){
        List<TransactionEntity> transactions = getAccount(id).getTransactions();
        transactions.sort(Comparator.comparing(TransactionEntity::getDate).reversed());

        return transactions;
    }

    public List<AccountEntity> getAccountByName(String name){
        return this.accountRepository.findByNameOwnerContaining(name);
    }
}
