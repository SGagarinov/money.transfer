package com.transfer.money.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardNotFoundException extends RuntimeException {

    public CardNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
