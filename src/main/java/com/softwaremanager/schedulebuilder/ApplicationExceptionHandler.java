package com.softwaremanager.schedulebuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.softwaremanager.schedulebuilder.Exception.ShiftNotAssociatedToEmployee;
import com.softwaremanager.schedulebuilder.Exception.DuplicateEmployeeException;
import com.softwaremanager.schedulebuilder.Exception.DuplicateShiftExeption;
import com.softwaremanager.schedulebuilder.Exception.EmployeeAlreadyClockedInException;
import com.softwaremanager.schedulebuilder.Exception.EmployeeAlreadyHaveThisShiftException;
import com.softwaremanager.schedulebuilder.Exception.EmployeeClockedOutAlreadyException;
import com.softwaremanager.schedulebuilder.Exception.EmployeeNotClockedInException;
import com.softwaremanager.schedulebuilder.Exception.EmployeeNotFoundException;
import com.softwaremanager.schedulebuilder.Exception.NoDataForLaborCostException;
import com.softwaremanager.schedulebuilder.Exception.NotAuthorizedUsers;
import com.softwaremanager.schedulebuilder.Exception.ErrorResponse;

import com.softwaremanager.schedulebuilder.Exception.ShiftNotFoundException;
import com.softwaremanager.schedulebuilder.Exception.TimeCardNotFoundException;
import com.softwaremanager.schedulebuilder.Exception.UserNotFoundException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ EmployeeNotFoundException.class, ShiftNotFoundException.class,
            ShiftNotAssociatedToEmployee.class, NoDataForLaborCostException.class, TimeCardNotFoundException.class,
            UserNotFoundException.class })
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorResponse(Arrays.asList(ex.getMessage())), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ DuplicateShiftExeption.class, DuplicateEmployeeException.class })
    public ResponseEntity<Object> handleDuplicateShidts(RuntimeException ex) {
        return new ResponseEntity<Object>(new ErrorResponse(Arrays.asList(ex.getMessage())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ EmployeeAlreadyClockedInException.class, EmployeeNotClockedInException.class,
            EmployeeClockedOutAlreadyException.class, NotAuthorizedUsers.class })
    public ResponseEntity<ErrorResponse> handleEmployeeAlreadyClockIn(RuntimeException ex) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(Arrays.asList(ex.getMessage())),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ EmployeeAlreadyHaveThisShiftException.class })
    public ResponseEntity<ErrorResponse> handleSameEmployeeInTheSameShift(RuntimeException ex) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(Arrays.asList(ex.getMessage())),
                HttpStatus.BAD_REQUEST);
    }

}
