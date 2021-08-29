package com.acmebank.account_manager.api.controller;

import com.acmebank.account_manager.entity.TransferRequest;
import com.acmebank.account_manager.exception.AccountNotFoundException;
import com.acmebank.account_manager.exception.IllegalTransferAmountException;
import com.acmebank.account_manager.exception.InsufficientFundsException;
import com.acmebank.account_manager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
public class Controller {

    @Autowired
    AccountService accountService;

    @GetMapping(value="/v1/account/{accountNum}/balance")
    public @ResponseBody
    String getBalance(@PathVariable(value="accountNum") int accountNum) throws AccountNotFoundException {
        return accountService.getAccount(accountNum).getBalance().toEngineeringString();

    }

    @PostMapping(value="/v1/account/transfer")
    public @ResponseBody String transferBalance(@RequestBody(required = true) @Valid TransferRequest request)
            throws AccountNotFoundException, InsufficientFundsException, IllegalTransferAmountException {
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalTransferAmountException();
        accountService.transferBalance(
                    request.getFromAccount(),
                    request.getToAccount(),
                    request.getAmount());

        return "Transfer success";
    }
}
