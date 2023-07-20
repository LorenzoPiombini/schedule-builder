package com.softwaremanager.schedulebuilder.web;
import java.util.List;


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

import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.Shift;
import com.softwaremanager.schedulebuilder.service.ShiftServiceImpl;

import lombok.AllArgsConstructor;



@AllArgsConstructor
@RestController
@RequestMapping("v1/shift")
public class ShiftController {
    
    
    ShiftServiceImpl shiftService;

     
     
     @GetMapping("/{shiftId}")
    public ResponseEntity<Shift> showShift(@PathVariable Long shiftId){
        return new ResponseEntity<Shift>(shiftService.getShift(shiftId), HttpStatus.OK);
    }
    


    @PostMapping("/scheduleItem/{scheduleItemId}")
    public ResponseEntity<Shift> createShiftNoEmployee(@RequestBody Shift shift, @PathVariable Long scheduleItemId){
        return new ResponseEntity<>(shiftService.saveShift(shift,  scheduleItemId),HttpStatus.CREATED);
    }

     @PutMapping("/{shiftId}")
    public ResponseEntity<Shift> updateShift(@RequestBody Shift shift, @PathVariable Long shiftId){
        return new ResponseEntity<Shift>( shiftService.updateShift(shift.getStartTime(), shift.getEndTime(), shiftId),HttpStatus.OK);
    }

    @PutMapping("/{shiftId}/employee/{employeeId}/scheduleItem/{scheduleItemId}")
    public ResponseEntity<Shift> addShiftToEmployeeAndScheduleITem(@PathVariable Long shiftId, @PathVariable Long employeeId, @PathVariable Long scheduleItemId){
        return new ResponseEntity<>(shiftService.addShiftToEmployeeAndScheduleItem(shiftId, employeeId, scheduleItemId),HttpStatus.CREATED);
    }

     @DeleteMapping("/{shiftId}")
    public ResponseEntity<HttpStatus> deleteShift(@PathVariable Long shiftId){
        shiftService.deleteShift(shiftId);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Shift>> showAllShifts(){
        List<Shift> shifts = shiftService.getAllShifts();
        return new ResponseEntity<List<Shift>>(shifts, HttpStatus.OK);
    }

    @GetMapping("/{shiftId}/employee")
    public ResponseEntity<List<Employee>> getEmployeeShifts(@PathVariable Long shiftId){
        return new ResponseEntity<>(shiftService.getEmployeeShift(shiftId), HttpStatus.OK);
    }


}
