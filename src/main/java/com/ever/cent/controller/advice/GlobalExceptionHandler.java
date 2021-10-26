package com.ever.cent.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ever.cent.exception.ForbidenAccessException;
import com.ever.cent.exception.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ForbidenAccessException.class)
	protected ResponseEntity<String> handleForbiden(ForbidenAccessException ex, WebRequest request) {
        return new ResponseEntity<String>(ex.getMessage() ,HttpStatus.FORBIDDEN );
    }
	
	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<String> handleForbiden(NotFoundException ex, WebRequest request) {
        return new ResponseEntity<String>(ex.getMessage() ,HttpStatus.NOT_FOUND );
    }
	
	
}
