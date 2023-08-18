package com.softwaremanager.schedulebuilder.service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.softwaremanager.schedulebuilder.ComputingClasses.LaborCost;
import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.Shift;
import com.softwaremanager.schedulebuilder.Exception.DuplicateShiftExeption;
import com.softwaremanager.schedulebuilder.Exception.EmployeeAlreadyHaveThisShiftException;
import com.softwaremanager.schedulebuilder.Exception.ShiftNotAssociatedToEmployee;
import com.softwaremanager.schedulebuilder.Exception.ShiftNotFoundException;
import com.softwaremanager.schedulebuilder.repository.EmployeeRepository;
import com.softwaremanager.schedulebuilder.repository.ShiftRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class ShiftServiceImpl implements ShiftService {


    
    ShiftRepository shiftRepo;
    EmployeeRepository employeeRepo;
   

  
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
     // checkForDoubleItem(shift); 
     // i commented this out because with the new code refactor
     // there is no need to have a check for a duplicate entities, since the new system is counting 
     // on duplicate entities 
      return shiftRepo.save(shift);
   }

  


    @Override
   public Shift updateShift(LocalTime startTime, LocalTime endTime, LocalDate date, Long shiftId) {
      Shift shift = getShift(shiftId);
      shift.setStartTime(startTime);
      shift.setEndTime(endTime);
      shift.setDate(date);

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

       if(isShiftAlreadyAssignedToThisEmployee(employeeId, shiftToAdd)) throw new EmployeeAlreadyHaveThisShiftException(employeeId);
       
       Employee employee = EmployeeServiceImpl.optionalUnwrapper(employeeRepo.findById(employeeId),employeeId);
       
       
       employee.getShifts().add(shiftToAdd);
       shiftToAdd.getEmployees().add(employee);
       
       
       return shiftRepo.save(shiftToAdd);

   }
   
    @Override
   public Double getLaborCost(List<Shift> shifts, LocalDate date) {
      return LaborCost.computeLaborOfShift(shifts, date);
   }

   @Override
   public Double getWeekLaborCost() {
      
      return 0.0;
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
    
    updateShift(shiftToDeleteFromEmployee.getStartTime(), shiftToDeleteFromEmployee.getEndTime(), shiftToDeleteFromEmployee.getDate(), shiftId);
    employeeRepo.save(employee);
  
   }


   
   /**
    *  this method look in wich week we are in, then 
    *  return a {@code Map<LocalDate, Shift>}, which will be used to perform
    *  weekly labor cost computation
    * @return
    */
   private Map<LocalDate, Shift> findeShiftThisWeek(){
      Map<LocalDate, Shift> thisWeek = new HashMap<>();
      Map<LocalDate, DayOfWeek> week = new HashMap<>();

      LocalDate today = LocalDate.now();
      LocalDate startWeek = today.minusDays(today.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());
      

      for(int i = 0; i < 7; i++){
         week.put(startWeek, startWeek.getDayOfWeek());
         startWeek = startWeek.plusDays(1);
      }
      
      for (Shift shift : getAllShifts()) {
         LocalDate date = shift.getDate();

         if(thisWeek.containsKey(date)){
            thisWeek.put(shift.getDate(), shift);
         }

      }
      
      return thisWeek;
   }


   private boolean isShiftAlreadyAssignedToThisEmployee(Long employeeId, Shift shift){
      for (Employee e : shift.getEmployees()) {
         if(e.getId().equals(employeeId)) return true;
      }
      return false;
   }
 


  

  


   }