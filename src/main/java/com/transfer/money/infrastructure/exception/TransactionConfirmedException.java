package com.transfer.money.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class TransactionConfirmedException extends RuntimeException {
    public TransactionConfirmedException(String errorMessage) {
        super(errorMessage);
    }
}
