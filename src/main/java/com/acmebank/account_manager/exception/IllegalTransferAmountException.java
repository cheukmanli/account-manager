package com.acmebank.account_manager.exception;

public class IllegalTransferAmountException extends Exception {

    public IllegalTransferAmountException() {
        super("Transfer amount must be greater than zero");
    }
}
