package com.softwaremanager.schedulebuilder.service;

import java.time.LocalTime;
import java.util.List;

import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.Shift;

public interface ShiftService {
    Shift getShift(Long shiftId);
    Shift saveShift(Shift shift, Long scheduleItemId);
    Shift updateShift(LocalTime startTime, LocalTime endTime, Long shiftId);
    Shift addShiftToEmployeeAndScheduleItem(Long shiftId, Long employeeId, Long scheduleItemId);
    void deleteShift(Long shiftId);
    List<Shift> getAllShifts();
    List<Employee> getEmployeeShift(Long shiftId);
    
}
