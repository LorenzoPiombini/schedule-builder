package com.softwaremanager.schedulebuilder;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.softwaremanager.schedulebuilder.Constant.Role;
import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.Shift;
import com.softwaremanager.schedulebuilder.repository.EmployeeRepository;
import com.softwaremanager.schedulebuilder.repository.ShiftRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@SpringBootApplication
public class ScheduleBuilderApplication implements CommandLineRunner{
    
	
	
	EmployeeRepository employeeRepository;
	ShiftRepository shiftRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ScheduleBuilderApplication.class, args);
	}

	// this code populates the inMemory (volatile) database H2 with data 
	// this is to help the developing project only.
	
	@Override
	public void run(String... args) throws Exception {
		Employee[] employees = new Employee[] {
			new Employee("Marco", "Bravo", 20, 17.5, "Cook",Role.USER),
			new Employee("Stephania", "Alfaro Vaga", 20, 14.13, "Cook", Role.USER),
            new Employee("David", "Arietta", 20, 17.5, "Cook", Role.USER),
			new Employee("Carlos", "Badilla Bejarano", 20, 17.5, "Dishwasher", Role.USER),
			new Employee("Maria", "Badilla Ramirez", 20, 17.5, "Assistant Manager", Role.USER),
            new Employee("Annabel", "Badilla", 20, 14.50 , "Cook", Role.USER),
			new Employee("Gertrude", "Bioh", 20, 17.5, "Cook", Role.USER),
			new Employee("Fernando", "Bravo", 20, 17.5, "Cook", Role.USER),
            new Employee("Tyree ", "Robert", 20, 17.5, "Cashier", Role.USER),
            new Employee("Victoria", "Campos", 20, 17.5, "Cook", Role.USER),
			new Employee("Victor", "Feliziano Fiorentino", 20, 17.5, "Cook", Role.USER),
            new Employee("Oscar ", "Garcia Bravo", 20, 17.5, "Cook", Role.USER),
		    new Employee("Tomin", "Garcia", 20, 17.5, "Cook", Role.USER),
			new Employee("Sarah", "Gavins", 20, 17.5, "Cook", Role.USER),
            new Employee("Melvin", "Granados", 20, 17.5, "Cook", Role.USER),
		    new Employee("Olman", "Guido", 20, 17.5, "Cook", Role.USER),
			new Employee("Omar", "Molina", 20, 17.5, "Cook", Role.USER),
            new Employee("Sandra", "Lubin", 20, 17.5, "Cook", Role.USER),
		    new Employee("Giovanni", "Do Santos", 20, 17.5, "Cook", Role.USER),
			new Employee("Angelita", "Martina Alvarez", 20, 17.5, "Cook", Role.USER),
            new Employee("Betsabeth", "Yanes", 20, 17.5, "Cook", Role.USER),
		    new Employee("Brigitte", "Mesa", 20, 17.5, "Cook", Role.USER),
			new Employee("Blanca", "Osorio", 20, 17.5, "Cook", Role.USER),
            new Employee("Ivie", "Prince Igieton", 20, 17.5, "Cook", Role.USER),
		    new Employee("Bernardita", "Ramirez Herrera", 20, 17.5, "Cook", Role.USER),
			new Employee("Juana", "Ramirez Visoso", 20, 17.5, "Cook", Role.USER),
            new Employee("Santiago", "Reyes", 20, 17.5, "Cook", Role.USER),
		    new Employee("Camilo", "Sanchez", 20, 17.5, "Cook", Role.USER),
			new Employee("Greta", "Rebazza", 20, 17.5, "Cook", Role.USER),
		    new Employee("Jorge", "Sanchez", 20, 17.5, "Cook", Role.USER),
		    new Employee("Alicia", "Threatts", 20, 17.5, "Cook", Role.USER),
			new Employee("Ada", "Patricia", 20, 17.5, "Cook", Role.USER),
		    new Employee("Yohana", "Martinez", 20, 17.5, "Cook", Role.USER)
		};

		for (int i = 0; i < employees.length; i++) {
			// we save twice each employee entity
			// to compute the timeCardEmployeeId which is based on
			// the entity id, the later is generated only the first time
			// you saved the entity to the database
			employeeRepository.save(employees[i]);
			employees[i].setTimeCardEmployeeId(); // computing the timeCard
			employeeRepository.save(employees[i]);
		}

		
        //a week worth of schedule 
		Shift[] shifts = new Shift[]{
			new Shift(LocalTime.of(10, 0, 0), LocalTime.of(17, 0, 0), LocalDate.of(2023, 7, 24)),
			new Shift(LocalTime.of(11, 0, 0), LocalTime.of(17, 0, 0),LocalDate.of(2023, 7, 24)),
			new Shift(LocalTime.of(11, 0, 0), LocalTime.of(20, 30, 0),LocalDate.of(2023, 7, 24)),
			new Shift(LocalTime.of(16, 0, 0), LocalTime.of(20, 30, 0),LocalDate.of(2023, 7, 24)),

			new Shift(LocalTime.of(10, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 25)),
			new Shift(LocalTime.of(10, 0, 0), LocalTime.of(17, 0, 0), LocalDate.of(2023, 7, 25)),
			new Shift(LocalTime.of(11, 0, 0), LocalTime.of(17, 0, 0),LocalDate.of(2023, 7, 25)),
            new Shift(LocalTime.of(16, 0, 0), LocalTime.of(20, 30, 0),LocalDate.of(2023, 7, 25)),
			
			new Shift(LocalTime.of(10, 0, 0), LocalTime.of(17, 0, 0), LocalDate.of(2023, 7, 26)),
			new Shift(LocalTime.of(10, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 26)),
			new Shift(LocalTime.of(11, 0, 0), LocalTime.of(20, 30, 0),LocalDate.of(2023, 7, 26)),
			
			new Shift(LocalTime.of(10, 0, 0), LocalTime.of(17, 0, 0), LocalDate.of(2023, 7, 27)),
            new Shift(LocalTime.of(10, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 27)),
			new Shift(LocalTime.of(11, 0, 0), LocalTime.of(20, 30, 0),LocalDate.of(2023, 7, 27)),
			 new Shift(LocalTime.of(16, 0, 0), LocalTime.of(20, 30, 0),LocalDate.of(2023, 7, 27)),

			 new Shift(LocalTime.of(10, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 28)),
			 new Shift(LocalTime.of(11, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 28)),
			 new Shift(LocalTime.of(11, 0, 0), LocalTime.of(17, 0, 0),LocalDate.of(2023, 7, 28)),
		
			 new Shift(LocalTime.of(10, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 29)),
			 new Shift(LocalTime.of(11, 0, 0), LocalTime.of(17, 0, 0),LocalDate.of(2023, 7, 29)),
			 new Shift(LocalTime.of(10, 0, 0), LocalTime.of(17, 0, 0),LocalDate.of(2023, 7, 29)),
			 new Shift(LocalTime.of(9, 30, 0), LocalTime.of(20, 30, 0),LocalDate.of(2023, 7, 29)),
			
			 new Shift(LocalTime.of(10, 0, 0), LocalTime.of(18, 30, 0), LocalDate.of(2023, 7, 30)),
			 new Shift(LocalTime.of(11, 0, 0), LocalTime.of(17, 0, 0),LocalDate.of(2023, 7, 29)),
			};

		for (int index = 0; index < shifts.length; index++) {
			shiftRepository.save(shifts[index]);
		}
	}
	

}
