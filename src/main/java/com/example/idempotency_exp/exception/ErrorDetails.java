package com.example.idempotency_exp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorDetails {

    private String localDateTime;
    private String message;
    private String details;

}
