package Mockito.homework.MockitoSpring.service;

import Mockito.homework.MockitoSpring.exception.EmployeeNotFoundException;
import Mockito.homework.MockitoSpring.model.Employee;
import Mockito.homework.MockitoSpring.service.api.DepartmentService;
import Mockito.homework.MockitoSpring.service.api.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    EmployeeService employeeService;  // Мокируем зависимость

    @InjectMocks
    DepartmentService departmentService;  // Внедряем мок в объект для тестирования

    @BeforeEach
    void setUp() {
        // Мокируем EmployeeService, чтобы он возвращал нужные данные
        when(employeeService.getAllEmployees()).thenReturn(
                List.of(
                        new Employee("Ваня", "Петров", 1, 100000),
                        new Employee("Паша", "Борисов", 1, 150000),
                        new Employee("Илья", "Власов", 1, 120000),
                        new Employee("Михаил", "Смирнов", 2, 7000),
                        new Employee("Анна", "Иванова", 2, 10000),
                        new Employee("Зинаида", "Кузнецова", 2, 15000)
                ));
    }

    @Test
    void allEmployee() {
        List<Employee> employees = departmentService.getEmployeesByDepartment(1);
        assertEquals(3, employees.size());
    }

    @Test
    void sum() {
        double actual = departmentService.getTotalSalaryByDepartment(2);
        assertEquals(32000, actual);
    }

    @Test
    void min() {
        double actual = departmentService.getMinSalaryByDepartment(1);
        assertEquals(100000, actual);
    }

    @Test
    void max() {
        double actual = departmentService.getMaxSalaryByDepartment(2);
        assertEquals(15000, actual);
    }

    @Test
    void testGetMaxSalaryByDepartmentWhenNoEmployees() {
        assertThrows(EmployeeNotFoundException.class, () -> {
            departmentService.getMaxSalaryByDepartment(6);
        });
    }
}
