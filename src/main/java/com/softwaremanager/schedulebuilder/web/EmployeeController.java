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
import com.softwaremanager.schedulebuilder.service.EmployeeServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("v1/employee")
@Tag(name = "Employee Controller", description = """
        Controller dedicated to manage Employees entity,
        all CRUD operations are managed by this Controller's Endpoints.
         """)
public class EmployeeController {

    private EmployeeServiceImpl service;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> showEmployee(@PathVariable Long id) {
        return new ResponseEntity<Employee>(service.getEmployee(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(service.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeId,
            @Valid @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(service.updateEmployee(employeeId, employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
        service.delateEployee(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> showAllEmployee() {
        List<Employee> employees = service.getAllEmployee();
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/{employeeId}/shifts")
    public ResponseEntity<List<Shift>> getShifts(@PathVariable Long employeeId) {
        return new ResponseEntity<>(service.getAllShiftsForAEmployee(employeeId), HttpStatus.OK);
    }

    @PostMapping("/{employeeId}/users/create")
    public ResponseEntity<HttpStatus> createUser(@PathVariable Long employeeId) {
        service.generateUser(employeeId);
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

}
