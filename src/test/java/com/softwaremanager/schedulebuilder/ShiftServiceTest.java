package com.softwaremanager.schedulebuilder;



import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.softwaremanager.schedulebuilder.Constant.Role;
import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.Shift;
import com.softwaremanager.schedulebuilder.repository.EmployeeRepository;
import com.softwaremanager.schedulebuilder.repository.ShiftRepository;
import com.softwaremanager.schedulebuilder.service.EmployeeServiceImpl;
import com.softwaremanager.schedulebuilder.service.ShiftServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ShiftServiceTest {
    
    @Mock
    private ShiftRepository shiftRepo;

    @Mock
    private EmployeeRepository employeeRepo;

    @Mock
    private EmployeeServiceImpl employeeService;

    

    @InjectMocks
    private ShiftServiceImpl shiftService;

    @Test
    public void getShiftsTest(){
        Shift shift = new Shift(LocalTime.of(12, 0, 0), LocalTime.of(16, 0, 0), LocalDate.of(2023,8,21));

    
        when(shiftRepo.findById(1l)).thenReturn(Optional.of(shift));

        Shift result = shiftService.getShift(1l);

    // Explicitly compare fields using equals and assertTrue
    assertTrue(shift.getStartTime().equals(result.getStartTime()), "Start times should be equal");
    assertTrue(shift.getEndTime().equals(result.getEndTime()), "End times should be equal");
    assertTrue(shift.getDate().equals(result.getDate()), "Dates should be equal");

    verify(shiftRepo).findById(1L);
        
    }


    @Test
    public void updateShiftTest(){

        //this is mocking the getShift() method since updateShift calls it
        Shift shift = new Shift(LocalTime.of(12, 0, 0), LocalTime.of(16, 0, 0), LocalDate.of(2023,8,21));
        when(shiftRepo.findById(1l)).thenReturn(Optional.of(shift));
        Shift result = shiftService.getShift(1l);
        

        LocalDate newdate = LocalDate.of(2022, 1, 1);
        LocalTime newStart = LocalTime.of(20, 0, 0);
        LocalTime newEnd = LocalTime.of(23, 0, 0);

        shiftService.updateShift(newStart,newEnd,newdate,1L);

        // Explicitly compare fields using equals and assertTrue
    assertTrue(result.getStartTime().equals(newStart), "Start times should be equal");
    assertTrue(shift.getEndTime().equals(newEnd), "End times should be equal");
    assertTrue(shift.getDate().equals(newdate), "Dates should be equal");


    }

    @Test
    public void addShiftToEmplyeeTest(){

        //this is mocking the EMployeeRepo used by this method
        Employee employeeExample = new Employee("Olman", "Guido", 20, 17.5, "Cook", Role.USER);
        //this is mocking the getShift() method, since addShiftToEmployee calls it
        Shift shift = new Shift(LocalTime.of(12, 0, 0), LocalTime.of(16, 0, 0), LocalDate.of(2023,8,21));

        shift.setEmployees(new ArrayList<>());
        employeeExample.setShifts(new ArrayList<>());

        when(shiftRepo.findById(1L)).thenReturn(Optional.of(shift));
        when(employeeRepo.findById(1L)).thenReturn(Optional.of(employeeExample));
        
       
         shiftService.addShiftToEmployee(1L, 1L);

        assertTrue(employeeExample.getShifts().contains(shift));
        assertTrue(shift.getEmployees().contains(employeeExample));

    }


    @Test
    public void deleteShftAssociatedWithEmployeeTest(){
        //this is mocking the EMployeeRepo used by this method
        Employee employeeExample = new Employee("Olman", "Guido", 20, 17.5, "Cook", Role.USER);
        //this is mocking the getShift() method, since addShiftToEmployee calls it
        Shift shift = new Shift(LocalTime.of(12, 0, 0), LocalTime.of(16, 0, 0), LocalDate.of(2023,8,21));
   
        
        shift.setEmployees(new ArrayList<>());
        employeeExample.setShifts(new ArrayList<>());

        when(shiftRepo.findById(1L)).thenReturn(Optional.of(shift));
        when(employeeRepo.findById(1L)).thenReturn(Optional.of(employeeExample));
        
        //adding shift to employee
        shiftService.addShiftToEmployee(1L, 1L);

        //testing if the delete method works
        shiftService.deleteShftAssociatedWithEmployee(1L, 1L);

        assertTrue(shift.getEmployees().isEmpty());
        assertTrue(employeeExample.getShifts().isEmpty());

    }
}
