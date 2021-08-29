package com.acmebank.accountmanager.api.controller;

import com.acmebank.accountmanager.exception.AccountNotFoundException;
import com.acmebank.accountmanager.exception.IllegalTransferAmountException;
import com.acmebank.accountmanager.exception.InsufficientFundsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String
    handleInvalidRequest() {
        return "Request body invalid, please check and try again";
    }

    @ExceptionHandler(value = AccountNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public String handleAccountNotFound(AccountNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = {InsufficientFundsException.class, IllegalTransferAmountException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleInsufficientBalance(Exception e) {
        return e.getMessage();
    }

}
