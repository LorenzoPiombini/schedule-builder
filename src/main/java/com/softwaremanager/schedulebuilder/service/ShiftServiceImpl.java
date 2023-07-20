package com.softwaremanager.schedulebuilder.service;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.ScheduleItem;
import com.softwaremanager.schedulebuilder.Entity.Shift;
import com.softwaremanager.schedulebuilder.Exception.DuplicateShiftExeption;
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
   public Shift saveShift(Shift shift,  Long scheduleItemId) {
      checkForDoubleItem(shift);
      Optional<ScheduleItem> scheduleItem = scheduleItemRepo.findById(scheduleItemId);
      ScheduleItem unwrappedScheduleITem = ScheduleItemServiceImp.unwrapScheduleItem(scheduleItem, scheduleItemId);
      
      shift.setScheduleItem(unwrappedScheduleITem);
   
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
      shiftRepo.deleteById(shiftId);
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
   public Shift addShiftToEmployeeAndScheduleItem(Long shiftId, Long employeeId, Long scheduleItemId) {
       Shift shiftToAdd = getShift(shiftId);
       Employee employee = EmployeeServiceImpl.optionalUnwrapper(employeeRepo.findById(employeeId),employeeId);
       
       employee.getShifts().add(shiftToAdd);
       shiftToAdd.getEmployees().add(employee);
       shiftToAdd.setScheduleItem(ScheduleItemServiceImp.unwrapScheduleItem(scheduleItemRepo.findById(scheduleItemId), scheduleItemId));
       
       return shiftRepo.save(shiftToAdd);

   }


   public void checkForDoubleItem(Shift shift){
      for (Shift shiftSaved: getAllShifts()) {
          if (shiftSaved.getEndTime().equals(shift.getEndTime()) && shiftSaved.getStartTime().equals(shift.getStartTime())){
             throw new DuplicateShiftExeption(shiftSaved);
          }
      }
   }


  

  


   }