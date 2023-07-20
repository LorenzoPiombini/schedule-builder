package com.softwaremanager.schedulebuilder.web;

import com.softwaremanager.schedulebuilder.Entity.ScheduleItem;
import com.softwaremanager.schedulebuilder.Entity.Shift;
import com.softwaremanager.schedulebuilder.service.ScheduleItemServiceImp;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Set;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("v1/scheduleItem")
public class ScheduleItemController {
    
    ScheduleItemServiceImp scheduleItemService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleItem> getScheduleItem(@PathVariable Long id){
        return new ResponseEntity<>(scheduleItemService.getSchduleItem(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ScheduleItem> saveScheduleItem(@Valid @RequestBody ScheduleItem scheduleItem){
        return new ResponseEntity<ScheduleItem>(scheduleItemService.saveScheduleItem(scheduleItem),HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<ScheduleItem>> getScheduleItems(){
        return new ResponseEntity<>(scheduleItemService.getScheduleItems(),HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleItemId}")
    public ResponseEntity<HttpStatus> deleteScheduleItem(Long scheduleItemId){
        scheduleItemService.deleteScheduleItem(scheduleItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{scheduleItemId}")
    public ResponseEntity<ScheduleItem> updateScheduleItem(@PathVariable Long scheduleItemId, @RequestBody ScheduleItem scheduleItem){
        return new ResponseEntity<>(scheduleItemService.updateScheduleItem(scheduleItemId, scheduleItem),HttpStatus.OK);
    }

    @PutMapping("/{scheduleItemId}/employee/{employeeId}/shift/{shiftId}")
    public ResponseEntity<ScheduleItem> addSchiftToScheduleItem(@PathVariable Long scheduleItemId, @PathVariable Long shiftId, @PathVariable Long employeeId){
        return new ResponseEntity<>(scheduleItemService.addShiftToScheduleItem(shiftId, scheduleItemId, employeeId),HttpStatus.OK);
    }


    @GetMapping("/{scheduleItemId}/shifts")
    public ResponseEntity<List<Shift>> getSchduleItemSchifts(@PathVariable Long scheduleItemId ){
        return new ResponseEntity<>(scheduleItemService.getScheduleItemShifts(scheduleItemId),HttpStatus.OK);
    }
    

}
