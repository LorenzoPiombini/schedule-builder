package com.softwaremanager.schedulebuilder.Exception;

public class EmployeeAlreadyHaveThisShiftException extends RuntimeException {
    public EmployeeAlreadyHaveThisShiftException(Long id){
        super("Employee with id:"+ id +"is already assigned to this shift");
    }
}
