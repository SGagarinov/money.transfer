package com.transfer.money.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidCardInfoException extends RuntimeException {
    public InvalidCardInfoException(String errorMessage) {
        super(errorMessage);
    }
}