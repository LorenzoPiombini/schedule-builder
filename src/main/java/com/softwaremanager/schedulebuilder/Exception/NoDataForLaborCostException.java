package com.softwaremanager.schedulebuilder.Exception;

public class NoDataForLaborCostException extends RuntimeException{
    public NoDataForLaborCostException(){
        super("there is no data to compute the labor Cost. please make sure you have built the schedule");
    }
}
