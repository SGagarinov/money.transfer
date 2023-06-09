package com.transfer.money.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidVerificationCodeException extends RuntimeException {
    public InvalidVerificationCodeException(String errorMessage) {
        super(errorMessage);
    }
}
