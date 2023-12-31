package com.softwaremanager.schedulebuilder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.softwaremanager.schedulebuilder.Constant.Constant;
import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.Shift;
import com.softwaremanager.schedulebuilder.Entity.Users;
import com.softwaremanager.schedulebuilder.Exception.DuplicateEmployeeException;
import com.softwaremanager.schedulebuilder.Exception.EmployeeNotFoundException;
import com.softwaremanager.schedulebuilder.repository.EmployeeRepository;
import com.softwaremanager.schedulebuilder.repository.UsersRepository;
import com.softwaremanager.sortingClasses.SortEmployee;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

   private EmployeeRepository employeeRepo;
   private UsersRepository userRepo;
   private BCryptPasswordEncoder econder;

   @Override
   public Employee getEmployee(Long id) {
      return optionalUnwrapper(employeeRepo.findById(id), id);
   }

   @Override
   public Employee saveEmployee(Employee employee) {
      checkDuplicateEmployee(employee);

      // we save the entity here, because we need the id for the timeCard.
      employeeRepo.save(employee);

      // this method is to set the timeCardId that the employee will use;
      employee.setTimeCardEmployeeId();

      // we save the employee with the timeCardId generated;
      return employeeRepo.save(employee);
   }

   @Override
   public Employee updateEmployee(Long employeeId, Employee employee) {
      Employee oldEmployee = getEmployee(employeeId);
      updatingEmployee(oldEmployee, employee);
      return employeeRepo.save(oldEmployee);
   }

   @Override
   public void delateEployee(Long id) {
      employeeRepo.deleteById(id);
   }

   @Override
   public List<Employee> getAllEmployee() {
      SortEmployee sorter = new SortEmployee();
      List<Employee> employees = (List<Employee>) employeeRepo.findAll();
      sorter.sortByLastName(employees);
      return employees;
   }

   @Override
   public List<Shift> getAllShiftsForAEmployee(Long employeeId) {
      Employee employee = getEmployee(employeeId);
      return employee.getShifts();
   }

   /**
    * method name {@code optionalUnwrapper}
    * <p>
    * this method safely unwraps the Optional value.
    * </p>
    * <p>
    * if the optional value is {@code null} this function throws a new
    * {@code EmployeeNotFoundException}.
    * </p>
    * 
    * @param entity
    * @param id
    * @return {@code Employee}
    * 
    */
   static Employee optionalUnwrapper(Optional<Employee> entity, Long id) {
      if (entity.isPresent())
         return entity.get();
      else
         throw new EmployeeNotFoundException(id);
   }

   /**
    * method name: updatingEmployee
    * <p>
    * this function update every field of the old employee instance
    * </p>
    * 
    * @param oldEmployee
    * @param employee
    */

   static void updatingEmployee(Employee oldEmployee, Employee employee) {
      oldEmployee.setFirstName(employee.getFirstName());
      oldEmployee.setLastName(employee.getLastName());
      oldEmployee.setHourlyRate(employee.getHourlyRate());
      oldEmployee.setDob(employee.getDob());
      oldEmployee.setJobTitle(employee.getJobTitle());
      oldEmployee.setRole(employee.getRole());
   }

   public void checkDuplicateEmployee(Employee employee) {
      for (Employee employeeSaved : getAllEmployee()) {
         if (employeeSaved.getFirstName().equalsIgnoreCase(employee.getFirstName())
               && employeeSaved.getLastName().equalsIgnoreCase(employee.getLastName())) {
            throw new DuplicateEmployeeException(employeeSaved);
         }
      }
   }

   @Override
   public void generateUser(Long EmployeeId) {
      Employee employee = optionalUnwrapper(employeeRepo.findById(EmployeeId), EmployeeId);
      Users user = new Users();
      user.setEmployee(employee);
      user.setUsername(employee.getFirstName() + employee.getLastName());
      String password = employee.getLastName() + Constant.BASE_TIME_CARD_ID;
      user.setPassword(econder.encode(password));
      userRepo.save(user);
   }

}