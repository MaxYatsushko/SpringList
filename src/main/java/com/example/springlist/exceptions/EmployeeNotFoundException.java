package com.example.springlist.exceptions;

public class EmployeeNotFoundException extends EmployeeException {
    public EmployeeNotFoundException(String message, int errorCode) {
        super(message, errorCode);
    }
}
