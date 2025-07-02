package com.ecommerce.backend.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.util.*;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(Map.of(
                "timestamp", new Date(),
                "message", ex.getMessage(),
                "status", HttpStatus.BAD_REQUEST.value()
        ), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException ex) {
        return new ResponseEntity<>(Map.of(
                "timestamp", new Date(),
                "message", "Token has expired",
                "status", HttpStatus.UNAUTHORIZED.value()
        ), HttpStatus.UNAUTHORIZED);
    }

}
