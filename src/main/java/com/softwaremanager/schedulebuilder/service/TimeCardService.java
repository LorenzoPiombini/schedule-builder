package com.softwaremanager.schedulebuilder.service;

import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.TimeCard;

public interface TimeCardService {
   TimeCard clockIn(Employee employee);
}
