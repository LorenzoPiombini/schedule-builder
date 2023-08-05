package com.softwaremanager.schedulebuilder.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.softwaremanager.schedulebuilder.Entity.ScheduleItem;
import com.softwaremanager.schedulebuilder.Entity.Shift;
import com.softwaremanager.schedulebuilder.Exception.ScheduleItemNotFoundException;
import com.softwaremanager.schedulebuilder.repository.EmployeeRepository;
import com.softwaremanager.schedulebuilder.repository.ScheduleItemRepository;
import com.softwaremanager.schedulebuilder.repository.ShiftRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ScheduleItemServiceImp implements ScheduleItemService {
    
    
    ScheduleItemRepository scheduleItemRepository;
    ShiftRepository shiftRepository;
    EmployeeRepository employeeRepository;

    @Override
    public ScheduleItem getSchduleItem(Long id) {
        Optional<ScheduleItem> scheduleItem = scheduleItemRepository.findById(id);
        return unwrapScheduleItem(scheduleItem, id);
    }

    @Override
    public ScheduleItem saveScheduleItem(ScheduleItem scheduleItem) {
       return scheduleItemRepository.save(scheduleItem);
    }

    @Override
    public ScheduleItem updateScheduleItem(Long scheduleItemId, ScheduleItem scheduleItem) {
        ScheduleItem unwrappScheduleItem = unwrapScheduleItem(scheduleItemRepository.findById(scheduleItemId), scheduleItemId);
        unwrappScheduleItem.setDate(scheduleItem.getDate());
        unwrappScheduleItem.setForecastedSalesForTheDay(scheduleItem.getForecastedSalesForTheDay());
        return scheduleItemRepository.save(unwrappScheduleItem);
    }

    @Override
    public void deleteScheduleItem(Long id) {
        scheduleItemRepository.deleteById(id);
    }

    @Override
    public ScheduleItem addShiftToScheduleItem(Long shiftId, Long scheduleItemId) {
        ScheduleItem scheduleItem = getSchduleItem(scheduleItemId);
        Optional<Shift> shift = shiftRepository.findById(shiftId);
        Shift unwrappedShift = ShiftServiceImpl.unwrapShift(shift, shiftId);

        scheduleItem.getShiftInTheScheduleItem().add(unwrappedShift);
        unwrappedShift.getScheduleItems().add(scheduleItem);
        shiftRepository.save(unwrappedShift);

        return scheduleItemRepository.save(scheduleItem);
    }

    @Override
    public List<ScheduleItem> getScheduleItems() {
        return (List<ScheduleItem>) scheduleItemRepository.findAll();
    }

    @Override
    public List<Shift> getScheduleItemShifts(Long id) {
        ScheduleItem scheduleItem = getSchduleItem(id);
        return scheduleItem.getShiftInTheScheduleItem();
    }

    static ScheduleItem  unwrapScheduleItem(Optional<ScheduleItem> entity, Long scheduleItemId){
        if(entity.isPresent()) return entity.get();
        else throw new ScheduleItemNotFoundException(scheduleItemId);
    }

    

    
    
}
