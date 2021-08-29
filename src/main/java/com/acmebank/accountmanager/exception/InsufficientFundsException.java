package com.acmebank.accountmanager.exception;

public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
        super("There is insufficient funds");
    }
}
