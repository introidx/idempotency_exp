package com.example.idempotency_exp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TokenNotFoundException extends RuntimeException{

    public TokenNotFoundException(String message) {
        super(message);
    }
}
