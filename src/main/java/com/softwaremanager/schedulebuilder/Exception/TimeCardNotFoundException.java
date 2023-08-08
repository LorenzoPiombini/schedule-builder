package com.softwaremanager.schedulebuilder.Exception;

public class TimeCardNotFoundException extends RuntimeException {
    public TimeCardNotFoundException(Long employeeId){
        super("Tim card for this employee(id:"+ employeeId +") not found");
    }
}
