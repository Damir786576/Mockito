package Mockito.homework.MockitoSpring.controller;

import Mockito.homework.MockitoSpring.model.Employee;
import Mockito.homework.MockitoSpring.service.api.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class    DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByDepartment(@PathVariable int id) {
        return departmentService.getEmployeesByDepartment(id);
    }

    @GetMapping("/{id}/salary/sum")
    public double getTotalSalaryByDepartment(@PathVariable int id) {
        return departmentService.getTotalSalaryByDepartment(id);
    }

    @GetMapping("/{id}/salary/max")
    public double getMaxSalaryByDepartment(@PathVariable int id) {
        return departmentService.getMaxSalaryByDepartment(id);
    }

    @GetMapping("/{id}/salary/min")
    public double getMinSalaryByDepartment(@PathVariable int id) {
        return departmentService.getMinSalaryByDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getEmployeesByDepartments() {
        return departmentService.getEmployeesByDepartments();
    }
}
