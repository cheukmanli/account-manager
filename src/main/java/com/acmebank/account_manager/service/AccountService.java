package com.acmebank.account_manager.service;

import com.acmebank.account_manager.entity.Account;
import com.acmebank.account_manager.exception.AccountNotFoundException;
import com.acmebank.account_manager.exception.InsufficientFundsException;
import com.acmebank.account_manager.repository.AccountRepository;
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
