package com.softwaremanager.schedulebuilder.Exception;

public class ScheduleItemNotFoundException extends RuntimeException {
    
    public ScheduleItemNotFoundException(Long id){
        super("A scheduleItem with id '" + id + "' does not exist in our records");
    }
}
