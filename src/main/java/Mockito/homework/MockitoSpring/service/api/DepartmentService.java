package Mockito.homework.MockitoSpring.service.api;

import Mockito.homework.MockitoSpring.exception.EmployeeNotFoundException;
import Mockito.homework.MockitoSpring.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;


    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Метод для получения сотрудников по ID отдела
    public List<Employee> getEmployeesByDepartment(int departmentId) {
        List<Employee> employeeList = (List<Employee>) employeeService.getAllEmployees();
        return employeeList.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    public double getTotalSalaryByDepartment(int departmentId) {
        List<Employee> employeeList = (List<Employee>) employeeService.getAllEmployees();
        return employeeList.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public double getMaxSalaryByDepartment(int departmentId) {
        List<Employee> employeeList = (List<Employee>) employeeService.getAllEmployees();
        return employeeList.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("В отделе не найдено сотрудников"))
                .getSalary();
    }


    public double getMinSalaryByDepartment(int departmentId) {
        List<Employee> employeeList = (List<Employee>) employeeService.getAllEmployees();
        return employeeList.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("В отделе не найдено сотрудников"))
                .getSalary();
    }

    public Map<Integer, List<Employee>> getEmployeesByDepartments() {
        List<Employee> employeeList = (List<Employee>) employeeService.getAllEmployees();
        return employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
