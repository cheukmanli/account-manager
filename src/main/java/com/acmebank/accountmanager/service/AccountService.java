package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.entity.Account;
import com.acmebank.accountmanager.exception.AccountNotFoundException;
import com.acmebank.accountmanager.exception.InsufficientFundsException;
import com.acmebank.accountmanager.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account getAccount(int accountNum) throws AccountNotFoundException{
        Optional<Account> account = accountRepository.findById(accountNum);
        if(account.isPresent()) return account.get();
        throw new AccountNotFoundException(accountNum);
    }

    @Transactional
    public void transferBalance(int fromAccountNum, int toAccountNum, BigDecimal amount)
            throws AccountNotFoundException, InsufficientFundsException {
        Account fromAccount = getAccount(fromAccountNum);
        Account toAccount = getAccount(toAccountNum);
        if (fromAccount.getBalance().compareTo(amount) < 0) throw new InsufficientFundsException();
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));
    }
}
