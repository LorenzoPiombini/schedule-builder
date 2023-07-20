package com.softwaremanager.schedulebuilder.Exception;

public class EmployeeNotFoundException extends RuntimeException{
    
    public EmployeeNotFoundException(Long id){
        super("The Employee id '" + id + "' does not exist in our records");
    }
    
}
