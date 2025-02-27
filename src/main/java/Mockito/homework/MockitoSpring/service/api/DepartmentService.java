package Mockito.homework.MockitoSpring.service.api;

import Mockito.homework.MockitoSpring.exception.EmployeeNotFoundException;
import Mockito.homework.MockitoSpring.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;

@Service
public class DepartmentService {

    private final List<Employee> employeeList;

    public DepartmentService() {
        employeeList = new ArrayList<>(List.of(
                new Employee("Василий", "Пупкин", 1, 55000),
                new Employee("Анна", "Трубецкая", 2, 54000),
                new Employee("Петр", "Корабелкин", 3, 58000),
                new Employee("Ирина", "Иванова", 4, 67000),
                new Employee("Семен", "Крупский", 5, 15000),
                new Employee("Алексей", "Шабанов", 4, 127000),
                new Employee("Зинаида", "Семенова", 1, 45000),
                new Employee("Давид", "Мартиросян", 4, 18700000),
                new Employee("Анастасия", "Турцкевич", 3, 58000),
                new Employee("Лидия", "Разумихина", 2, 76000)
        ));;
    }

    public List<Employee> getEmployeesByDepartment(int departmentId) {
        return employeeList.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    public double getTotalSalaryByDepartment(int departmentId) {
        return employeeList.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public double getMaxSalaryByDepartment(int departmentId) {
        return employeeList.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("В отделе не найдено сотрудников"))
                .getSalary();
    }

    public double getMinSalaryByDepartment(int departmentId) {
        return employeeList.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("В отделе не найдено сотрудников"))
                .getSalary();
    }

    public Map<Integer, List<Employee>> getEmployeesByDepartments() {
        return employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
