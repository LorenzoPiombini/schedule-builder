package com.softwaremanager.schedulebuilder.Exception;

public class EmployeeClockedOutAlreadyException extends RuntimeException{
    public EmployeeClockedOutAlreadyException(Long id){
        super("Employee with id:"+id+" clocked Out Already!");
    }
}
