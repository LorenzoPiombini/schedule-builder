package com.softwaremanager.schedulebuilder.ComputingClasses;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.Shift;



public class LaborCost {

    private static Map<LocalDate,Double> laborCostsWeek = new HashMap<>();
 
    //labor cost computation for a single day
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
           setLaborCostsWeek(date,result);
        return result;
    }


    public static void setLaborCostsWeek(LocalDate date, Double value){
        laborCostsWeek.put(date, value);
    }


    public static Map<LocalDate, Double> getLaborCostsWeek(){
        return laborCostsWeek;
    }

    public static Double laborCostWeek(){
        Double result = 0.0;
        for(Double value : getLaborCostsWeek().values()){
            result += value;
        }
       return result;
    }

    
}