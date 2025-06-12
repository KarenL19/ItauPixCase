package com.store.itaupixcase.infra.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", HttpStatus.UNPROCESSABLE_ENTITY.value());
        List<String> messages = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .toList();
        response.put("message", messages);
        response.put("errorCode", "ERROR_VALIDATION");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("uuid", UUID.randomUUID().toString());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.put("message", ex.getMessage());
        response.put("errorCode", "ILLEGAL_ARGUMENT");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("uuid", UUID.randomUUID().toString());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchElementException(NoSuchElementException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", HttpStatus.NOT_FOUND.value());
        response.put("message", ex.getMessage());
        response.put("errorCode", "NOT_FOUND");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("uuid", UUID.randomUUID().toString());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}