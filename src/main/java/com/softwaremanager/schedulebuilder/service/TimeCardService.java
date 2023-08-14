package com.softwaremanager.schedulebuilder.service;

import java.util.List;

import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.TimeCard;

public interface TimeCardService {
   TimeCard clockIn(Integer timeCardEmployeeId);
   TimeCard clockOut(Integer timeCardEmployeeId);
   List<TimeCard> findAll();
   List<TimeCard> findAllByEmployeeId(Employee employee);
   Long timeCardidConverter(Integer timeCardEmployeeId);
}
