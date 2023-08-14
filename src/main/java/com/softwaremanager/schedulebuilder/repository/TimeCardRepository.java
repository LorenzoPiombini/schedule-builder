package com.softwaremanager.schedulebuilder.repository;
import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.TimeCard;


import java.time.LocalDateTime;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface TimeCardRepository extends CrudRepository<TimeCard, Long> {
    List<TimeCard> findByEmployee(Employee employee);

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
