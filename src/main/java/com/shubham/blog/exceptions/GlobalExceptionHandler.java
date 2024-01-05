package com.shubham.blog.exceptions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundExceptionHandler(ResourceNotFoundException e)
	{
		String message=e.getMessage();
		
		return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException e)
	{
		 Map<String,String> responseMap=new LinkedHashMap<>();
		 e.getBindingResult().getAllErrors()
		 .forEach(error->{
			 String field=((FieldError) error).getField();
			 String message=error.getDefaultMessage();
			 
			 responseMap.put(field, message);
			}
		 
		 );
		 
		 return new ResponseEntity<>(responseMap,HttpStatus.BAD_REQUEST);
	}
}
