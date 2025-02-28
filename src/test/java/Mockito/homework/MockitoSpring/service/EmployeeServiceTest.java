package Mockito.homework.MockitoSpring.service;

import Mockito.homework.MockitoSpring.model.Employee;
import Mockito.homework.MockitoSpring.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {
    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();


    @BeforeEach
    void workEmployee() {
        employeeService.addEmployee("Миша", "Борисов", 1, 250000);
        employeeService.addEmployee("Ваня", "Петров", 2, 250001);
        employeeService.addEmployee("Данил", "Долгов", 3, 25);
        employeeService.addEmployee("Дима", "Решалов", 1, 250);
    }

    @Test
    void findAll() {
        Collection<Employee> actual = employeeService.getAllEmployees();
        assertEquals(4, actual.size());
    }
    @Test
    void find() {
        Employee actual = employeeService.findEmployee("Ваня", "Петров");
        assertEquals("Ваня", actual.getFirstName());
        assertEquals("Петров", actual.getLastName());
        assertEquals("Ваня Петров", actual.getFullName());
    }
    @Test
    void remove() {
        int size = employeeService.getAllEmployees().size();
        employeeService.removeEmployee("Ваня", "Петров");
        assertEquals(size - 1, employeeService.getAllEmployees().size());
    }
    @Test
    void add() {
        int size = employeeService.getAllEmployees().size();
        employeeService.addEmployee("Паша", "Мартынов", 3, 250002);
        assertEquals(size + 1, employeeService.getAllEmployees().size());
    }
}
