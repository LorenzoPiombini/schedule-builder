package com.softwaremanager.schedulebuilder.service;

import java.time.LocalTime;
import java.util.List;

import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.Shift;

public interface ShiftService {
    Shift getShift(Long shiftId);
    Shift saveShift(Shift shift);
    Shift updateShift(LocalTime startTime, LocalTime endTime, Long shiftId);
    Shift addShiftToEmployee(Long shiftId, Long employeeId);
    Shift addShiftToScheduleItem(Long shiftId, Long scheduleItemId);
    void deleteShift(Long shiftId);
    void deleteShftAssociatedWithEmployee(Long shiftId, Long employeeId);
    List<Shift> getAllShifts();
    List<Employee> getEmployeeShift(Long shiftId);
    
}
