package com.softwaremanager.schedulebuilder.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.TimeCard;
import com.softwaremanager.schedulebuilder.Exception.TimeCardNotFoundException;
import com.softwaremanager.schedulebuilder.repository.TimeCardRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class TimeCardServiceImp implements TimeCardService  {

    
 TimeCardRepository timeCardRepo;

@Override
public TimeCard clockIn(Employee employee) {
    return timeCardRepo.save(timeCardRepo.clockIn(employee));
}

@Override
public TimeCard clockOut(Long timeCardId) {
    TimeCard timecard = unwrapOptional(timeCardRepo.findById(timeCardId), timeCardId);
    return timeCardRepo.save(timeCardRepo.clockOut(timecard));
}



static TimeCard unwrapOptional(Optional<TimeCard> timeCard, Long timeCardId){
    if(timeCard.isPresent()) return timeCard.get();
    else throw new TimeCardNotFoundException(timeCardId);
}


    
 
   
    
}
