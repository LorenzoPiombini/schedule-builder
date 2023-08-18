package com.softwaremanager.schedulebuilder.ComputingClasses;

import java.time.LocalDate;
import java.util.List;

import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.Shift;

//tou need to rethink all of this 

public class LaborCost {

    public static double computeLaborOfShift(List<Shift> shifts, LocalDate date){
        if(shifts.isEmpty()) return 0.0;

       

        double result = 0.0;
        
         
        for (Shift shift : shifts) {
            if(!shift.getDate().equals(date)) continue;
            
            double shiftDuration = shift.getShiftDuration();
             for(Employee e : shift.getEmployees()){ 
             result += e.getHourlyRate() * shiftDuration;
        }
        }
        
        return result;
    }

    
}