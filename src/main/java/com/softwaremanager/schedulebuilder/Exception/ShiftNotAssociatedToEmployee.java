package com.softwaremanager.schedulebuilder.Exception;

public class ShiftNotAssociatedToEmployee extends RuntimeException {
    
    public ShiftNotAssociatedToEmployee(Long shiftId, Long employeeId){
        super("The shift with id:" +shiftId+", is not associated with this Employee id:"+ employeeId);
    }
}
