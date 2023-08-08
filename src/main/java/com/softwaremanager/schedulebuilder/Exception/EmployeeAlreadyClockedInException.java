package com.softwaremanager.schedulebuilder.Exception;

public class EmployeeAlreadyClockedInException extends RuntimeException {
    public EmployeeAlreadyClockedInException(Long employeeId){
        super("This Employee is already clocked-in!");
    }
}
