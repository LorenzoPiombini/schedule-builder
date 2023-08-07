package com.softwaremanager.schedulebuilder.service;

import org.springframework.stereotype.Service;

import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.TimeCard;
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
    
 
   
    
}
