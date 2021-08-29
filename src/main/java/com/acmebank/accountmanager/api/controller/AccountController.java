package com.acmebank.accountmanager.api.controller;

import com.acmebank.accountmanager.entity.TransferRequest;
import com.acmebank.accountmanager.exception.AccountNotFoundException;
import com.acmebank.accountmanager.exception.IllegalTransferAmountException;
import com.acmebank.accountmanager.exception.InsufficientFundsException;
import com.acmebank.accountmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping(value="/v1/account/{accountNum}/balance")
    public ResponseEntity<String> getBalance(@PathVariable(value="accountNum") int accountNum) throws AccountNotFoundException {
        return new ResponseEntity<>(accountService.getAccount(accountNum).getBalance().toEngineeringString(), HttpStatus.OK);

    }

    @PostMapping(value="/v1/account/transfer")
    public ResponseEntity<String> transferBalance(@RequestBody(required = true) @Valid TransferRequest request)
            throws AccountNotFoundException, InsufficientFundsException, IllegalTransferAmountException {
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalTransferAmountException();
        accountService.transferBalance(
                    request.getFromAccount(),
                    request.getToAccount(),
                    request.getAmount());

        return new ResponseEntity<>("Transfer success", HttpStatus.ACCEPTED);
    }
}
