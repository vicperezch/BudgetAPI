package com.myapp.budget.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<String> UserAlreadyExistsExceptionHandler(UserAlreadyExistsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = RoleNotFoundException.class)
    public ResponseEntity<String> RoleNotFoundExceptionHandler(RoleNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = TypeNotFoundException.class)
    public ResponseEntity<String> TypeNotFoundExceptionHandler(TypeNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = IllegalAccessException.class)
    public ResponseEntity<String> IllegalAccessExceptionHandler(IllegalAccessException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
