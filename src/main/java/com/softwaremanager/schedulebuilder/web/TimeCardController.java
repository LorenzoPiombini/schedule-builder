package com.softwaremanager.schedulebuilder.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.TimeCard;
import com.softwaremanager.schedulebuilder.service.EmployeeServiceImpl;
import com.softwaremanager.schedulebuilder.service.TimeCardServiceImp;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("v1/timecard")
public class TimeCardController {

    TimeCardServiceImp timeCardService;
    EmployeeServiceImpl employeeService;


    @PostMapping(value = "/clockin")
    public ResponseEntity<TimeCard> handletClockIn(@RequestParam Integer timeCardEmployeeId){
        //find the employee from timeCardEmployeeId
        Employee employee = employeeService.getEmployee(employeeService.timeCardidConverter(timeCardEmployeeId));
        
        return new ResponseEntity<TimeCard>(timeCardService.clockIn(employee),HttpStatus.OK);
    }

    @PostMapping(value = "/clockout")
    public ResponseEntity<TimeCard> handletClockOut(@RequestParam Integer timeCardEmployeeId){
        //implement 
       return new ResponseEntity<TimeCard>(HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<TimeCard> getTimeCards(){
        return new ResponseEntity<TimeCard>(HttpStatus.OK);
    }
  
    
}
