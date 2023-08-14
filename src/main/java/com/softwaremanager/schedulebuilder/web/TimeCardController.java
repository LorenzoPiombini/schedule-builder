package com.softwaremanager.schedulebuilder.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.softwaremanager.schedulebuilder.Entity.TimeCard;
import com.softwaremanager.schedulebuilder.service.TimeCardServiceImp;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("v1/timecard")
public class TimeCardController {

    TimeCardServiceImp timeCardService;
   


    @PostMapping(value = "/clockin")
    public ResponseEntity<TimeCard> handletClockIn(@RequestParam Integer timeCardEmployeeId){
        return new ResponseEntity<TimeCard>(timeCardService.clockIn(timeCardEmployeeId),HttpStatus.OK);
    }

    @PostMapping(value = "/clockout")
    public ResponseEntity<TimeCard> handletClockOut(@RequestParam Integer timeCardEmployeeId){
        return new ResponseEntity<TimeCard>(timeCardService.clockOut(timeCardEmployeeId),HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<List<TimeCard>> getTimeCards(){
        return new ResponseEntity<List<TimeCard>>(timeCardService.findAll(),HttpStatus.OK);
    }
  
    
}
