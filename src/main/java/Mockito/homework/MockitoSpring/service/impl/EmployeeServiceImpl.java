package Mockito.homework.MockitoSpring.service.impl;

import Mockito.homework.MockitoSpring.model.Employee;
import Mockito.homework.MockitoSpring.service.api.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = employees.remove(firstName + " " + lastName);
        if (employee == null) {
            throw new RuntimeException("Такого сотрудника нет");
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = employees.get(firstName + " " + lastName);
        if (employee == null) {
            throw new RuntimeException("Такого сотрудника нет");
        }
        return employee;
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return employees.values();
    }
}
