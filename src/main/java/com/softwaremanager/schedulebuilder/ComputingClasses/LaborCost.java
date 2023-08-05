package com.softwaremanager.schedulebuilder.ComputingClasses;

import java.util.ArrayList;
import java.util.List;

import com.softwaremanager.schedulebuilder.Entity.ScheduleItem;
import com.softwaremanager.schedulebuilder.Exception.NoDataForLaborCostException;




public class LaborCost {
    private static List<Double> laborCostsEachShift = new ArrayList<>();
    
    private static List<Double> computeLaborCost(ScheduleItem scheduleItem){
        if(scheduleItem.getShiftInTheScheduleItem().size() == 0) throw new NoDataForLaborCostException();
    
        for (int i = 0; i < scheduleItem.getShiftInTheScheduleItem().size() ; i++) {
            for (int j = 0; j < scheduleItem.getShiftInTheScheduleItem().get(i).getEmployees().size() ; j++) {
               Double shiftLaborCost =  scheduleItem.getShiftInTheScheduleItem().get(i).getEmployees().get(j).getHourlyRate() * 
                                        scheduleItem.getShiftInTheScheduleItem().get(i).getShiftDuration() ;
               laborCostsEachShift.add(shiftLaborCost); 
            }
        }

        return laborCostsEachShift;
    }

    public static Double getLaborCost(ScheduleItem scheduleItem){
        double result = 0.0;
        for (Double laborCostShift : computeLaborCost(scheduleItem)) {
            result += laborCostShift.doubleValue();
        }

        return result;
    }
}
