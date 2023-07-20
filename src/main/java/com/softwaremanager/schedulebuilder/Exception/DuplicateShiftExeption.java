package com.softwaremanager.schedulebuilder.Exception;

import com.softwaremanager.schedulebuilder.Entity.Shift;

public class DuplicateShiftExeption extends RuntimeException{
    
    public DuplicateShiftExeption(Shift shift){
        super("This Shift already exist with id:"+shift.getId()+". Please use the one that you have created.");
    }
}
