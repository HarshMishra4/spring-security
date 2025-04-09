package com.harshmishra4.springsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * @author Harsh
 * @created 21/03/2025 - 2:46 AM
 */

@RestControllerAdvice
public class GExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUserException(Exception ex) {
        HashMap<String,String> map = new HashMap<>();
        map.put("message", ex.getMessage());
        map.put("status", HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
