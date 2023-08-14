package com.gutotech.loteriasapi.rest.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gutotech.loteriasapi.model.exception.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLoteriaNotFound(ResourceNotFoundException exception,
	    HttpServletRequest request) {
	ErrorResponse errorResponse = new ErrorResponse(
		HttpStatus.NOT_FOUND.value(), 
		HttpStatus.NOT_FOUND.getReasonPhrase(),
		exception.getMessage(),
		System.currentTimeMillis(),
		request.getRequestURI());
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleExceptions(Exception exception,
	    HttpServletRequest request) {
	ErrorResponse errorResponse = new ErrorResponse(
		HttpStatus.BAD_REQUEST.value(),
		HttpStatus.BAD_REQUEST.getReasonPhrase(), 
		exception.getMessage(),
		System.currentTimeMillis(), 
		request.getRequestURI());
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
