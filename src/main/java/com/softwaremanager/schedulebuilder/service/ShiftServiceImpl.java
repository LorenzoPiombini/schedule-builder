package com.softwaremanager.schedulebuilder.service;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.ScheduleItem;
import com.softwaremanager.schedulebuilder.Entity.Shift;
import com.softwaremanager.schedulebuilder.Exception.DuplicateShiftExeption;
import com.softwaremanager.schedulebuilder.Exception.ShiftNotAssociatedToEmployee;
import com.softwaremanager.schedulebuilder.Exception.ShiftNotFoundException;
import com.softwaremanager.schedulebuilder.repository.EmployeeRepository;
import com.softwaremanager.schedulebuilder.repository.ScheduleItemRepository;
import com.softwaremanager.schedulebuilder.repository.ShiftRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class ShiftServiceImpl implements ShiftService {


    
    ShiftRepository shiftRepo;
    EmployeeRepository employeeRepo;
    ScheduleItemRepository scheduleItemRepo;

  
   static Shift unwrapShift(Optional<Shift> entity, Long shiftId){
     if (entity.isPresent()) return entity.get();
     else throw new ShiftNotFoundException(shiftId);
   }


   @Override
   public Shift getShift(Long shiftId) {
      Optional<Shift> shift = shiftRepo.findById(shiftId);
      return unwrapShift(shift, shiftId);
   }



   @Override
   public Shift saveShift(Shift shift) {
      checkForDoubleItem(shift);

      
      
   
      return shiftRepo.save(shift);
   }

  


    @Override
   public Shift updateShift(LocalTime startTime, LocalTime endTime, Long shiftId) {
      Shift shift = getShift(shiftId);
      shift.setStartTime(startTime);
      shift.setEndTime(endTime);
      return shiftRepo.save(shift);
   }


   @Override
   public void deleteShift(Long shiftId) {
      Optional<Shift> shift = shiftRepo.findById(shiftId);
      if(shift.isPresent()){
         shiftRepo.deleteById(shiftId);
      } else {
         throw new ShiftNotFoundException(shiftId);
      }
      
   }


   @Override
   public List<Shift> getAllShifts() {
      return (List<Shift>) shiftRepo.findAll();
   }


   @Override
   public List<Employee> getEmployeeShift(Long shiftId) {
      Shift shift = getShift(shiftId);
      return shift.getEmployees();
   }


   @Override
   public Shift addShiftToEmployee(Long shiftId, Long employeeId) {
       Shift shiftToAdd = getShift(shiftId);
       Employee employee = EmployeeServiceImpl.optionalUnwrapper(employeeRepo.findById(employeeId),employeeId);
       
      
       employee.getShifts().add(shiftToAdd);
       shiftToAdd.getEmployees().add(employee);
       
       
       return shiftRepo.save(shiftToAdd);

   }
   
   @Override
   public Shift addShiftToScheduleItem(Long shiftId, Long scheduleItemId) {
      
      Shift shiftToAdd = getShift(shiftId);
      ScheduleItem scheduleItem = ScheduleItemServiceImp.unwrapScheduleItem(scheduleItemRepo.findById(scheduleItemId), scheduleItemId);

      scheduleItem.getShiftInTheScheduleItem().add(shiftToAdd);
      shiftToAdd.getScheduleItems().add(scheduleItem);

      return shiftToAdd;

   }


   public void checkForDoubleItem(Shift shift){
      for (Shift shiftSaved: getAllShifts()) {
          if (shiftSaved.getEndTime().equals(shift.getEndTime()) && shiftSaved.getStartTime().equals(shift.getStartTime())){
             throw new DuplicateShiftExeption(shiftSaved);
          }
      }
   }


   @Override
   public void deleteShftAssociatedWithEmployee(Long shiftId, Long employeeId) {
      Employee employee = EmployeeServiceImpl.optionalUnwrapper(employeeRepo.findById(employeeId), employeeId);
      Shift shiftToDeleteFromEmployee = unwrapShift(shiftRepo.findById(shiftId), shiftId);
     
      
      if(!employee.getShifts().contains(shiftToDeleteFromEmployee) && !shiftToDeleteFromEmployee.getEmployees().contains(employee)){
         throw new ShiftNotAssociatedToEmployee(shiftId, employeeId);
      }

    for (int i = 0; i < shiftToDeleteFromEmployee.getEmployees().size(); i++) {
       if(shiftToDeleteFromEmployee.getEmployees().get(i).equals(employee)){
         shiftToDeleteFromEmployee.getEmployees().remove(i);
       }
    }

    for (int i = 0; i < employee.getShifts().size(); i++) {
      if(employee.getShifts().get(i).equals(shiftToDeleteFromEmployee)){
         employee.getShifts().remove(i);
      }

    }
    
    updateShift(shiftToDeleteFromEmployee.getStartTime(), shiftToDeleteFromEmployee.getEndTime(), shiftId);
    employeeRepo.save(employee);
  
   }


 


  

  


   }