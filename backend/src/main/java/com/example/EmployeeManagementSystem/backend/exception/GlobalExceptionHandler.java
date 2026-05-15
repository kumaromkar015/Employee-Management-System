package com.example.EmployeeManagementSystem.backend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public String handle(RuntimeException ex) {
		return ex.getMessage();
	}
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public Map<String, String> handleValidation(
	            MethodArgumentNotValidException ex) {

	        Map<String, String> errors = new HashMap<>();

	        ex.getBindingResult().getFieldErrors()
	                .forEach(error -> {
	                    errors.put(error.getField(),
	                               error.getDefaultMessage());
	                });

	        return errors;
	    }
}
