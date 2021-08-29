package com.acmebank.account_manager.exception;

public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
        super("There is insufficient funds");
    }
}
