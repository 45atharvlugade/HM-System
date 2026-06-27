package com.al.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.al.exceptions.*;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(PatientValueError.class)
	public ResponseEntity<String> patientValueIsNull(PatientValueError pve){
		return new ResponseEntity<String>(pve.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}