package com.example.springlist.exceptions;

public class EmployeeAlreadyAddedException extends EmployeeException {
    public EmployeeAlreadyAddedException(String message, int errorCode) {
        super(message, errorCode);
    }

}
