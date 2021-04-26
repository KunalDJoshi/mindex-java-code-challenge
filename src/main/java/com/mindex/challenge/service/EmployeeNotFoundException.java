package com.mindex.challenge.service;
/** 
 *EmployeeNotFoundException class extends Exception class. If the employee is not found then it will show error
 *message.
 *@author Kunal Joshi
 *version 1.0
 *@since 2021-04-25
 */
public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}