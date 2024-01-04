package com.shubham.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> resourceNotFoundExceptionHandler(ResourceNotFoundException e)
	{
		String message=e.getMessage();
		
		return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
	}
}
