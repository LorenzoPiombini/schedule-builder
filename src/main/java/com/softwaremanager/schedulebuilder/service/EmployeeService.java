package com.softwaremanager.schedulebuilder.service;
import java.util.List;

import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.Shift;


public interface EmployeeService {
    Employee getEmployee(Long id);
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Long employeeId, Employee employee);
    void delateEployee(Long id);
    List<Employee> getAllEmployee();
    List<Shift> getAllShiftsForAEmployee(Long employeeId);
    
    
}
