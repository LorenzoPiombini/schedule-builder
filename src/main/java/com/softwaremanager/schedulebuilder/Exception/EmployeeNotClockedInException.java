package com.softwaremanager.schedulebuilder.Exception;

public class EmployeeNotClockedInException extends RuntimeException{
    public EmployeeNotClockedInException(Long id){
        super("Employee with the id:" + id +" is not clocked in, or the shift is over");
    }
}
