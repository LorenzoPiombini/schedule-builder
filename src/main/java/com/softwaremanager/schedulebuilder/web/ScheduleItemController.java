package com.softwaremanager.schedulebuilder.web;

import com.softwaremanager.schedulebuilder.Entity.ScheduleItem;
import com.softwaremanager.schedulebuilder.Entity.Shift;
import com.softwaremanager.schedulebuilder.Exception.ErrorResponse;
import com.softwaremanager.schedulebuilder.service.ScheduleItemServiceImp;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;




@AllArgsConstructor
@RestController
@RequestMapping("v1/scheduleItem")
@Tag(name= "Schedule Item Controller",
description = """
        Controller in charge of managing the Schedule item( in other words: a day in your schedule)
        CRUD operations all covered by this Controller`s endppoints.
        """)
public class ScheduleItemController {
    
    ScheduleItemServiceImp scheduleItemService;
    
    @Operation(summary = "Retrive a schedule Item", description = """
            you can retrive a Schedule Item with this endpoint if what you are trying to fetch 
            is not present and exeption will be thrown and an error code of 404
            """)
    @ApiResponses(value ={
        @ApiResponse(responseCode = "200", description = "Succesfully retrive the Schedule Item", content = @Content(schema = @Schema(implementation = ScheduleItem.class))),
        @ApiResponse(responseCode = "404", description = "Schedule Item not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduleItem> getScheduleItem(@PathVariable Long id){
        return new ResponseEntity<>(scheduleItemService.getSchduleItem(id),HttpStatus.OK);
    }

     @Operation(summary = "Creates a Schedule Entity", description = """
            you can create Schedule Item entities with this endpoint, a Schedule Item is a day 
            in your schedule, you can add shifts to a Schedule Item. Every day(scheduleItem) has a budget 
            this field is necessery for computing costs for the ooperation.
            """)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduleItem> saveScheduleItem(@Valid @RequestBody ScheduleItem scheduleItem){
        return new ResponseEntity<ScheduleItem>(scheduleItemService.saveScheduleItem(scheduleItem),HttpStatus.CREATED);
    }

    @Operation(summary = "Read all the schedule items", description = """
            you can retrive a list of all the schedlue items.
            """)
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ScheduleItem>> getScheduleItems(){
        return new ResponseEntity<>(scheduleItemService.getScheduleItems(),HttpStatus.OK);
    }

    @Operation(summary = "Delete a Schedule Item",
     description = """
            you can delate the schedule item corresponding the scheduleItemId that you pass with this
            endpoint, keep in mind that when you delate a Schedule item you delate all the Shifts in it
            """)
    @DeleteMapping(value="/{scheduleItemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteScheduleItem(@PathVariable Long scheduleItemId){
        System.out.println(scheduleItemId);
        scheduleItemService.deleteScheduleItem(scheduleItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{scheduleItemId}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduleItem> updateScheduleItem(@PathVariable Long scheduleItemId, @RequestBody ScheduleItem scheduleItem){
        return new ResponseEntity<>(scheduleItemService.updateScheduleItem(scheduleItemId, scheduleItem),HttpStatus.OK);
    }

    @PutMapping(value = "/{scheduleItemId}/employee/{employeeId}/shift/{shiftId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduleItem> addSchiftToScheduleItem(@PathVariable Long scheduleItemId, @PathVariable Long shiftId, @PathVariable Long employeeId){
        return new ResponseEntity<>(scheduleItemService.addShiftToScheduleItem(shiftId, scheduleItemId, employeeId),HttpStatus.OK);
    }


    @GetMapping(value = "/{scheduleItemId}/shifts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Shift>> getSchduleItemSchifts(@PathVariable Long scheduleItemId ){
        return new ResponseEntity<>(scheduleItemService.getScheduleItemShifts(scheduleItemId),HttpStatus.OK);
    }
    

}
