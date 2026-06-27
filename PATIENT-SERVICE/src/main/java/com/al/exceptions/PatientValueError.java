package com.al.exceptions;

public class PatientValueError extends RuntimeException {

    public PatientValueError(String message) {
        super(message);
    }
}