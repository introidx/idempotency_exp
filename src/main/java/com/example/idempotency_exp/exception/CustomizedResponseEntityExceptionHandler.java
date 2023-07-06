package com.example.idempotency_exp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.*;


@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now().toString(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public final ResponseEntity<Object> handleTokenNotFoundException(Exception e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now().toString(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public final ResponseEntity<Object> handleInvalidTokenException(Exception e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now().toString(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CannotRemoveTokenException.class)
    public final ResponseEntity<Object> handleCanNotRemoveTokenException(Exception e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now().toString(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
