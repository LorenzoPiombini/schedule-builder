package com.softwaremanager.schedulebuilder.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softwaremanager.schedulebuilder.ComputingClasses.LaborCost;
import com.softwaremanager.schedulebuilder.Entity.ScheduleItem;
import com.softwaremanager.schedulebuilder.repository.ScheduleItemRepository;



@Service
public class LaborCostServiceImpl implements LaborCostService {
   
    @Autowired
    ScheduleItemRepository scheduleItemRepo;

    @Override
    public Double laborCost(Long scheduleItemId) {
       Optional<ScheduleItem> scheduleItemOP = scheduleItemRepo.findById(scheduleItemId);
       ScheduleItem  unWrappedScheduleItem = ScheduleItemServiceImp.unwrapScheduleItem(scheduleItemOP, scheduleItemId);
       
       double result = LaborCost.getLaborCost(unWrappedScheduleItem);
       return result;
    }
    
}
