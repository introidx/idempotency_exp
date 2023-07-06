package com.example.idempotency_exp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CannotRemoveTokenException extends RuntimeException{

    public CannotRemoveTokenException(String message) {
        super(message);
    }
}
