package com.example.springlist.exceptions;

public class EmployeeStorageIsFullException extends EmployeeException {

    public EmployeeStorageIsFullException(String message, int errorCode) {
        super(message, errorCode);
    }
}
