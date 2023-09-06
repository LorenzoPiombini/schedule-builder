package com.softwaremanager.schedulebuilder.web;
import java.time.LocalDate;
import java.util.List;



import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.Shift;
import com.softwaremanager.schedulebuilder.service.ShiftServiceImpl;
import com.softwaremanager.schedulebuilder.Exception.ErrorResponse;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;



@AllArgsConstructor
@RestController
@RequestMapping("v1/shift")
@Tag(name= "Shift Controller",
description = """
        all the CRUD operations regarding  Shifts entity, are managed by this Controller`s endpoint
        """)
public class ShiftController {
    
    
    ShiftServiceImpl shiftService;

     
     @Operation(summary = "retrives the shift with the id provided", 
        description ="""
              returns a JSON object with the shift entity.
             """)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Succesfully retrive the shidtshift", content = @Content(schema = @Schema(implementation = Shift.class))),
        @ApiResponse(responseCode="404", description = "Shift not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
     @GetMapping(value = "/{shiftId}", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<Shift> showShift(@PathVariable Long shiftId){
        return new ResponseEntity<Shift>(shiftService.getShift(shiftId), HttpStatus.OK);
    }
    

    @Operation(summary = "creates a new shift entity", description = """
            This endpoint create a new Shift enity.
            """)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shift> createShiftNoEmployee(@RequestBody Shift shift){
        return new ResponseEntity<>(shiftService.saveShift(shift),HttpStatus.CREATED);
    }

    @Operation(summary = "update a shift entity", description = """
            this endpoint will update The Sift entity whose id is provided
            """)
     @PutMapping(value = "/{shiftId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shift> updateShift(@RequestBody Shift shift, @PathVariable Long shiftId){
        return new ResponseEntity<Shift>( shiftService.updateShift(shift.getStartTime(), shift.getEndTime(), shift.getDate(), shiftId),HttpStatus.OK);
    }

    @Operation(summary = "Assign a shift to an Employee", description = """
            you can assign an Employee {employeeId}to a shift {shiftId} entity using this endpoint,
            do not forget that a shift is attached to a Schedule item{scheduleItemId}
            """)
    @PutMapping(value = "/{shiftId}/employee/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shift> addShiftToEmployee(@PathVariable Long shiftId, @PathVariable Long employeeId){
        return new ResponseEntity<>(shiftService.addShiftToEmployee(shiftId, employeeId),HttpStatus.CREATED);
    }

    

    @Operation(summary = "Delete a shift for a specific Employee")
    @DeleteMapping(value = "/{shiftId}/employee/{employeeId}")
    public ResponseEntity<HttpStatus> deleteShiftFromEmployee(@PathVariable Long shiftId, @PathVariable Long employeeId){
        shiftService.deleteShftAssociatedWithEmployee(shiftId, employeeId);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "delete a shift", description = """
            delate a Shift enity, this end point will be avaibale only to those useres whose role is set to ADMIN
            """)
    @DeleteMapping(value = "/{shiftId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteShift(@PathVariable Long shiftId){
        shiftService.deleteShift(shiftId);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "shows all shift present in your schedule", description = """
            return a list of shifts, a JSON array object.
            """)
    @GetMapping(value ="/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Shift>> showAllShifts(){
        List<Shift> shifts = shiftService.getAllShifts();
        return new ResponseEntity<List<Shift>>(shifts, HttpStatus.OK);
    }

    @Operation(summary = "shows all employee with the same shift", description = """
            it returns a list of Employees entities that share the same shift,
            you`ll refere to the the shift with the {shiftId}.
            """)
    @GetMapping(value= "/{shiftId}/employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> getEmployeeShifts(@PathVariable Long shiftId){
        return new ResponseEntity<>(shiftService.getEmployeeShift(shiftId), HttpStatus.OK);
    }


    @Operation(summary = "labor cost of single day", description = """
            this endpoint compute the labor cost of all the shift in the same date.
            """)
    @GetMapping(value = "/labor")
    public ResponseEntity<Double> getLaborCostDay(@RequestParam LocalDate date){
        return new ResponseEntity<Double>(shiftService.getLaborCost(shiftService.getAllShifts(),date),HttpStatus.OK);
    }


    @Operation(summary =" current week labor cost", description = """
            Curretn week labor cost, this data are just a forecast, since they do not take in 
            consideration the timeCard data
            """)
    @GetMapping(value = "/labor/week")
    public ResponseEntity<Double> getLaborCostWeek(){
        return new ResponseEntity<Double>(shiftService.getWeekLaborCost(),HttpStatus.OK);
    }

}
