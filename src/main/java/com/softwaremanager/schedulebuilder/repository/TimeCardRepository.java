package com.softwaremanager.schedulebuilder.repository;
import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.TimeCard;


import java.time.LocalDateTime;

import org.springframework.data.repository.CrudRepository;

public interface TimeCardRepository extends CrudRepository<TimeCard, Long> {
    
    default TimeCard clockIn(Employee employee){
        TimeCard timeCard = new TimeCard();
        
        timeCard.setClockIn(LocalDateTime.now());
        timeCard.setEmployee(employee);
        
        return timeCard;
    }

    default TimeCard clockOut(TimeCard timeCard){
        timeCard.setCloclOut(LocalDateTime.now());
        return timeCard;
    }




    
}
