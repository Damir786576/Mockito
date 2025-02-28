package Mockito.homework.MockitoSpring.service.api;

import Mockito.homework.MockitoSpring.model.Employee;
import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int department, double salary);
    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
    Collection<Employee> getAllEmployees();
}

