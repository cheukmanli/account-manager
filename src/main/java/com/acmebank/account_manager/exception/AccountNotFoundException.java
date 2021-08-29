package com.acmebank.account_manager.exception;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(int accountNum) {
        super(String.format("Account %d not found", accountNum));
    }
}
