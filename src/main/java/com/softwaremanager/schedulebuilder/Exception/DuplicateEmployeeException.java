package com.softwaremanager.schedulebuilder.Exception;

import com.softwaremanager.schedulebuilder.Entity.Employee;

public class DuplicateEmployeeException extends RuntimeException {
    
    public DuplicateEmployeeException(Employee employee){
        super("Employee with id '"+employee.getId()+"' exists in the database");
    }
}
