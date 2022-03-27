package com.edmwat.metropol.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice 
public class GlobalExceptionHandler {

	@ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity unAuthenticatedAccess(AuthenticationException unauthenticated) {
        return new ResponseEntity("Authentication Required", HttpStatus.UNAUTHORIZED);
    }
}
