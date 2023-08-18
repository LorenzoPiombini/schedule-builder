package com.softwaremanager.schedulebuilder.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.Shift;

public interface ShiftService {
    Shift getShift(Long shiftId);
    Shift saveShift(Shift shift);
    Shift updateShift(LocalTime startTime, LocalTime endTime, LocalDate date, Long shiftId);
    Shift addShiftToEmployee(Long shiftId, Long employeeId);
    void deleteShift(Long shiftId);
    void deleteShftAssociatedWithEmployee(Long shiftId, Long employeeId);
    List<Shift> getAllShifts();
    List<Employee> getEmployeeShift(Long shiftId);
    Double getLaborCost(List<Shift> shifts, LocalDate date);
    Double getWeekLaborCost();
}
