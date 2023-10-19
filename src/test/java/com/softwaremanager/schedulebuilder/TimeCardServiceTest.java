package com.softwaremanager.schedulebuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.softwaremanager.schedulebuilder.Constant.Role;
import com.softwaremanager.schedulebuilder.Entity.Employee;
import com.softwaremanager.schedulebuilder.Entity.TimeCard;
import com.softwaremanager.schedulebuilder.repository.EmployeeRepository;
import com.softwaremanager.schedulebuilder.repository.TimeCardRepository;
import com.softwaremanager.schedulebuilder.service.TimeCardServiceImp;

@RunWith(MockitoJUnitRunner.class)
public class TimeCardServiceTest {

    private LocalDate date = LocalDate.of(1987, 3, 2);
    @InjectMocks
    private TimeCardServiceImp timecardService;

    @Mock
    private TimeCardRepository timeCardRepo;

    @Mock
    private EmployeeRepository employeeRepo;

    @Test
    public void clockInTest() {

        // Mocking employee data
        Long id = 1L;
        int timeCardEmployeeId = 100 + id.intValue();
        Employee employeeTest = new Employee("Betsabeth", "Yanes", date, 17.5, "Cook", Role.USER);
        employeeTest.setTimeCardEmployeeId(timeCardEmployeeId);

        when(employeeRepo.findById(1L)).thenReturn(Optional.of(employeeTest));

        // mocking timecard data
        TimeCard timeCard = new TimeCard(1L, employeeTest, LocalDateTime.now(), null);
        when(timeCardRepo.clockIn(employeeTest)).thenReturn(timeCard);
        when(timeCardRepo.save(timeCard)).thenReturn(timeCard);

        TimeCard result = timecardService.clockIn(101);

        assertTrue(result != null);
        assertTrue(timeCard.getClockIn() != null);
        assertTrue(result.getClockIn() != null);

    }

    @Test
    public void clockOutTest() {
        // mock employee data
        Long id = 1L;
        int timeCardEmployeeId = 100 + id.intValue();
        Employee employeeTest = new Employee("Betsabeth", "Yanes", date, 17.5, "Cook", Role.USER);
        TimeCard timeCard = new TimeCard(1L, employeeTest, LocalDateTime.now(), null);
        TimeCard updatedTimecard = new TimeCard(1L, employeeTest, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10));
        employeeTest.setTimeCardEmployeeId(timeCardEmployeeId);
        employeeTest.setTimeCards(new ArrayList<>());
        employeeTest.getTimeCards().add(timeCard);

        when(employeeRepo.findById(1L)).thenReturn(Optional.of(employeeTest));
        when(timeCardRepo.findById(1L)).thenReturn(Optional.of(timeCard));
        when(timeCardRepo.clockOut(timeCard)).thenReturn(updatedTimecard);
        when(timeCardRepo.save(updatedTimecard)).thenReturn(updatedTimecard);

        TimeCard result = timecardService.clockOut(101);

        assertNotNull(result);
        assertTrue(result.getCloclOut() != null);
        assertEquals(result.getCloclOut(), updatedTimecard.getCloclOut());

    }
}
