package com.example.test.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EmployeeNotFoundAdvice extends ResponseEntityExceptionHandler {

	// Let Spring BasicErrorController handle the exception, we just override the
	// status code
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Object> springHandleNotFound(EmployeeNotFoundException ex) throws Exception {
		return new ResponseEntity<>(ExceptionMapper.convertToJSON(ex, ex.getTarget()).toMap(), HttpStatus.NOT_FOUND);
	}
}