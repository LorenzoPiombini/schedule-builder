package com.softwaremanager.schedulebuilder.Exception;

public class ShiftNotFoundException extends RuntimeException{
    public ShiftNotFoundException(Long shiftId){
        super("the Shift with id '"+ shiftId+"' does not exist in our records ");
    }
}
