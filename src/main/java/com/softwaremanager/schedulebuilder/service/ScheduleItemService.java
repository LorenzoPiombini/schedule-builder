package com.softwaremanager.schedulebuilder.service;

import java.util.List;
import java.util.Set;

import com.softwaremanager.schedulebuilder.Entity.ScheduleItem;
import com.softwaremanager.schedulebuilder.Entity.Shift;

public interface ScheduleItemService {
    ScheduleItem getSchduleItem(Long id);
    ScheduleItem saveScheduleItem(ScheduleItem scheduleItem);
    ScheduleItem updateScheduleItem(Long scheduleItemId, ScheduleItem scheduleItem);
    void deleteScheduleItem(Long id);
    ScheduleItem addShiftToScheduleItem(Long shiftId, Long scheduleItemId, Long employeeId);
    Set<ScheduleItem> getScheduleItems();
    List<Shift> getScheduleItemShifts(Long id);
    
}
