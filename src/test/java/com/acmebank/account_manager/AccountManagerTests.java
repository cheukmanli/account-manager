package com.acmebank.account_manager;

import com.acmebank.account_manager.entity.Account;
import com.acmebank.account_manager.exception.AccountNotFoundException;
import com.acmebank.account_manager.exception.InsufficientFundsException;
import com.acmebank.account_manager.repository.AccountRepository;
import com.acmebank.account_manager.service.AccountService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AccountManagerTests {

    @MockBean
    private AccountRepository mockedRepository;

    @Autowired
    private AccountService accountService;

    @BeforeEach
    public void init() {
        Account account1 = new Account(12345678, BigDecimal.valueOf(100));
        Account account2 = new Account(88888888, BigDecimal.valueOf(100));

        when(mockedRepository.findById(12345678)).thenReturn(Optional.of(account1));
        when(mockedRepository.findById(88888888)).thenReturn(Optional.of(account2));
    }

    @SneakyThrows
    @DisplayName("Returns Account if exists")
    @Test
    public void testGetExistingAccount() {
        Account account = accountService.getAccount(12345678);
        assert(account != null);
    }

    @SneakyThrows
    @DisplayName("Throws exception if not exists")
    @Test
    public void testGetNonExistingAccount() {
        Assertions.assertThrows(AccountNotFoundException.class, () -> accountService.getAccount(0));
    }

    @SneakyThrows
    @DisplayName("Balance changes after transfer")
    @Test
    public void testTransferMoney() {
        accountService.transferBalance(12345678, 88888888, BigDecimal.valueOf(1));
        assert(accountService.getAccount(12345678).getBalance().compareTo(BigDecimal.valueOf(99)) == 0);
        assert(accountService.getAccount(88888888).getBalance().compareTo(BigDecimal.valueOf(101)) == 0);
    }

    @SneakyThrows
    @DisplayName("Throws exception if not enough funds")
    @Test
    public void testInsufficientFunds() {
        Assertions.assertThrows(InsufficientFundsException.class, () -> {
            accountService.transferBalance(12345678, 88888888, BigDecimal.valueOf(99999));
        });
    }

}
