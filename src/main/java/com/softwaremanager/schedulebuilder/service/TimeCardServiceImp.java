package com.softwaremanager.schedulebuilder.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.softwaremanager.schedulebuilder.Constant.Constant;
import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.TimeCard;
import com.softwaremanager.schedulebuilder.Exception.EmployeeAlreadyClockedInException;
import com.softwaremanager.schedulebuilder.Exception.EmployeeClockedOutAlreadyException;
import com.softwaremanager.schedulebuilder.Exception.EmployeeNotClockedInException;
import com.softwaremanager.schedulebuilder.Exception.TimeCardNotFoundException;
import com.softwaremanager.schedulebuilder.repository.EmployeeRepository;
import com.softwaremanager.schedulebuilder.repository.TimeCardRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class TimeCardServiceImp implements TimeCardService  {

    
 TimeCardRepository timeCardRepo;
 EmployeeRepository employeeRepository;

@Override
public TimeCard clockIn(Integer timeCardEmployeeId) {
    //find the employee from timeCardEmployeeId
    Employee employee = EmployeeServiceImpl.optionalUnwrapper(employeeRepository.findById(timeCardidConverter(timeCardEmployeeId)), timeCardidConverter(timeCardEmployeeId));

    if(!isNewTimeCard(employee.getTimeCards())) throw new EmployeeAlreadyClockedInException(employee.getId());
     
    TimeCard timecard = timeCardRepo.clockIn(employee);

    return timeCardRepo.save(timecard);
}

@Override
public TimeCard clockOut(Integer timeCardEmployeeId) {
     
     //find the employee from timeCardEmployeeId
     Employee employee = EmployeeServiceImpl.optionalUnwrapper(employeeRepository.findById(timeCardidConverter(timeCardEmployeeId)), timeCardidConverter(timeCardEmployeeId));
    
     if(!isEmployeeClockedIn(employee.getTimeCards())) throw new EmployeeNotClockedInException(employee.getId());
   
     Long timeCardId = employee.getTimeCards().get(0).getId();
     
    TimeCard timecard = unwrapOptional(timeCardRepo.findById(timeCardId), timeCardId);
    
    if(timecard.getCloclOut() != null ) throw new EmployeeClockedOutAlreadyException(employee.getId());
    return timeCardRepo.save(timeCardRepo.clockOut(timecard));
}



static TimeCard unwrapOptional(Optional<TimeCard> timeCard, Long timeCardId){
    if(timeCard.isPresent()) return timeCard.get();
    else throw new TimeCardNotFoundException(timeCardId);
}

@Override
public List<TimeCard> findAll() {
   return (List<TimeCard>) timeCardRepo.findAll();
}

@Override
public List<TimeCard> findAllByEmployeeId(Employee employee) {
   return timeCardRepo.findByEmployee(employee);
}


 @Override
   public Long timeCardidConverter(Integer timeCardEmployeeId) {
     Long id = Integer.toUnsignedLong(timeCardEmployeeId - Constant.BASE_TIME_CARD_ID);
     return id;
   }

/**
 * this method  return true if the employee is not clocked-in yet, or is a different day,
 * therefore the Employee needs a new {@code TimeCard}.
 * @param 
 * @return boolean
 */

public boolean isNewTimeCard(List<TimeCard> timeCards){
     if(timeCards == null || timeCards.isEmpty() ) return true;
     
     for (TimeCard timeCard : timeCards) {
        if(timeCard.getClockIn().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()) return false;
     }

     return true;
   }


   private boolean isEmployeeClockedIn(List<TimeCard> timeCards){
      if(timeCards == null || timeCards.isEmpty() ) return false;


      for (TimeCard timeCard : timeCards) {
         if(timeCard.getClockIn() != null && timeCard.getCloclOut() == null){
            return true;
         }
      }

      return false;
   }

   
 
   
    
}
