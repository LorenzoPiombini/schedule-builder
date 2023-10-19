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
public class ScheduleBuilderApplication implements CommandLineRunner {

	private EmployeeRepository employeeRepo;
	private ShiftRepository shiftRepo;

	public static void main(String[] args) {
		SpringApplication.run(ScheduleBuilderApplication.class, args);
	}

	// this code populates the inMemory (volatile) database H2 with data
	// this is to help the developing project only.

	@Override
	public void run(String... args) throws Exception {
		LocalDate date = LocalDate.of(1987, 3, 2);
		Employee[] employees = new Employee[] {
				new Employee("Marco", "Bravo", date, 17.5, "Cook", Role.USER),
				new Employee("Stephania", "Alfaro Vaga", date, 14.13, "Cook", Role.USER),
				new Employee("David", "Arietta", date, 17.5, "Cook", Role.USER),
				new Employee("Carlos", "Badilla Bejarano", date, 17.5, "Dishwasher", Role.USER),
				new Employee("Maria", "Badilla Ramirez", date, 17.5, "Assistant Manager", Role.USER),
				new Employee("Annabel", "Badilla", date, 14.50, "Cook", Role.USER),
				new Employee("Gertrude", "Bioh", date, 17.5, "Cook", Role.USER),
				new Employee("Fernando", "Bravo", date, 17.5, "Cook", Role.USER),
				new Employee("Tyree ", "Robert", date, 17.5, "Cashier", Role.USER),
				new Employee("Victoria", "Campos", date, 17.5, "Cook", Role.USER),
				new Employee("Victor", "Feliziano Fiorentino", date, 17.5, "Cook", Role.USER),
				new Employee("Oscar ", "Garcia Bravo", date, 17.5, "Cook", Role.USER),
				new Employee("Tomin", "Garcia", date, 17.5, "Cook", Role.USER),
				new Employee("Sarah", "Gavins", date, 17.5, "Cook", Role.USER),
				new Employee("Melvin", "Granados", date, 17.5, "Cook", Role.USER),
				new Employee("Olman", "Guido", date, 17.5, "Cook", Role.USER),
				new Employee("Omar", "Molina", date, 17.5, "Cook", Role.USER),
				new Employee("Sandra", "Lubin", date, 17.5, "Cook", Role.USER),
				new Employee("Giovanni", "Do Santos", date, 17.5, "Cook", Role.USER),
				new Employee("Angelita", "Martina Alvarez", date, 17.5, "Cook", Role.USER),
				new Employee("Betsabeth", "Yanes", date, 17.5, "Cook", Role.USER),
				new Employee("Brigitte", "Mesa", date, 17.5, "Cook", Role.USER),
				new Employee("Blanca", "Osorio", date, 17.5, "Cook", Role.USER),
				new Employee("Ivie", "Prince Igieton", date, 17.5, "Cook", Role.USER),
				new Employee("Bernardita", "Ramirez Herrera", date, 17.5, "Cook", Role.USER),
				new Employee("Juana", "Ramirez Visoso", date, 17.5, "Cook", Role.USER),
				new Employee("Santiago", "Reyes", date, 17.5, "Cook", Role.USER),
				new Employee("Camilo", "Sanchez", date, 17.5, "Cook", Role.USER),
				new Employee("Greta", "Rebazza", date, 17.5, "Cook", Role.USER),
				new Employee("Jorge", "Sanchez", date, 17.5, "Cook", Role.USER),
				new Employee("Alicia", "Threatts", date, 17.5, "Cook", Role.USER),
				new Employee("Ada", "Patricia", date, 17.5, "Cook", Role.USER),
				new Employee("Yohana", "Martinez", date, 17.5, "Cook", Role.USER)
		};

		for (int i = 0; i < employees.length; i++) {
			employeeRepo.save(employees[i]);

			employees[i].setTimeCardEmployeeId();// creating timeCardId

			employeeRepo.save(employees[i]);

		}

		// a week worth of schedule
		Shift[] shifts = new Shift[] {
				new Shift(LocalTime.of(10, 0, 0), LocalTime.of(17, 0, 0), LocalDate.of(2023, 7, 24)),
				new Shift(LocalTime.of(11, 0, 0), LocalTime.of(17, 0, 0), LocalDate.of(2023, 7, 24)),
				new Shift(LocalTime.of(11, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 24)),
				new Shift(LocalTime.of(16, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 24)),

				new Shift(LocalTime.of(10, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 25)),
				new Shift(LocalTime.of(10, 0, 0), LocalTime.of(17, 0, 0), LocalDate.of(2023, 7, 25)),
				new Shift(LocalTime.of(11, 0, 0), LocalTime.of(17, 0, 0), LocalDate.of(2023, 7, 25)),
				new Shift(LocalTime.of(16, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 25)),

				new Shift(LocalTime.of(10, 0, 0), LocalTime.of(17, 0, 0), LocalDate.of(2023, 7, 26)),
				new Shift(LocalTime.of(10, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 26)),
				new Shift(LocalTime.of(11, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 26)),

				new Shift(LocalTime.of(10, 0, 0), LocalTime.of(17, 0, 0), LocalDate.of(2023, 7, 27)),
				new Shift(LocalTime.of(10, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 27)),
				new Shift(LocalTime.of(11, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 27)),
				new Shift(LocalTime.of(16, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 27)),

				new Shift(LocalTime.of(10, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 28)),
				new Shift(LocalTime.of(11, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 28)),
				new Shift(LocalTime.of(11, 0, 0), LocalTime.of(17, 0, 0), LocalDate.of(2023, 7, 28)),

				new Shift(LocalTime.of(10, 0, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 29)),
				new Shift(LocalTime.of(11, 0, 0), LocalTime.of(17, 0, 0), LocalDate.of(2023, 7, 29)),
				new Shift(LocalTime.of(10, 0, 0), LocalTime.of(17, 0, 0), LocalDate.of(2023, 7, 29)),
				new Shift(LocalTime.of(9, 30, 0), LocalTime.of(20, 30, 0), LocalDate.of(2023, 7, 29)),

				new Shift(LocalTime.of(10, 0, 0), LocalTime.of(18, 30, 0), LocalDate.of(2023, 7, 30)),
				new Shift(LocalTime.of(11, 0, 0), LocalTime.of(17, 0, 0), LocalDate.of(2023, 7, 29)),
		};

		for (int index = 0; index < shifts.length; index++) {
			shiftRepo.save(shifts[index]);
		}
	}

}
