package com.jelkada.task3.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.jelkada.task3.entity.Department;
import com.jelkada.task3.entity.Employee;
import com.jelkada.task3.entity.Project;
import com.jelkada.task3.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

  private final DepartmentService departmentService;

  // get all departments
  public DepartmentController(DepartmentService theDepartmentService) {
    departmentService = theDepartmentService;
  }

  @GetMapping
  public List<Department> getAllDepartments() {
    return departmentService.getAllDepartments();
  }

  // get department by id
  @GetMapping(path = "/{id}")
  public Department getDepartmentById(@PathVariable("id") int departmentId) {
    return departmentService.getDepartmentById(departmentId);
  }

  // update an existing department
  @PutMapping(path = "/{id}")
  public Department updateDepartment(@PathVariable("id") int departmentId, @RequestBody Department updatedDepartment) {
    return departmentService.updateDepartment(departmentId, updatedDepartment);
  }

  // partial update to an existing department
  @PatchMapping(path = "/{id}")
  public Department patchDepartment(@PathVariable("id") int departmentId, @RequestBody Map<String, Object> partialDepartment) {
    return departmentService.patchDepartment(departmentId, partialDepartment);
  }

  // delete department by department id
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<String> deleteDepartmentById(@PathVariable("id") int departmentId) {
    departmentService.deleteDepartmentById(departmentId);
    return ResponseEntity.status(200).body("Department was deleted successfully");
  }

  // get list of employees for each department
  // also implemented via the /employees endpoint
  @GetMapping(path = "/{id}/employees")
  public List<Employee> getEmployeesByDepartmentId(@PathVariable("id") int departmentId) {
    return departmentService.getEmployeesByDepartmentId(departmentId);
  }

  // get list of projects for each department
  @GetMapping(path = "/{id}/projects")
  public List<Project> getProjectsByDepartmentId(@PathVariable("id") int departmentId) {
    return departmentService.getProjectsByDepartmentId(departmentId);
  }

  // Count employees in each department
  @GetMapping(path = "/count-employees")
  public Map<String, Integer> countEmployeesPerDepartment() {
    List<Department> deptList = getAllDepartments();
    Map<String, Integer> employeeCount = new HashMap<>();

    deptList.forEach(dept -> employeeCount.put(dept.getName(), dept.getEmployees().size()));
    return employeeCount;
  }

  // Highest paid employee in each department
  @GetMapping(path = "/highest-paid-department")
  public Map<String, Employee> highestPaidPerDepartment() {
    List<Department> deptList = getAllDepartments();
    Map<String, Employee> highestPaidList = new HashMap<>();

    for (Department dept: deptList) {
      List<Employee> empList = dept.getEmployees();
      double highestSalary = 0.0;
      Employee tempEmployee = null;
      for (Employee emp: empList) {
        if (emp.getSalary() > highestSalary) {
          highestSalary = emp.getSalary();
          tempEmployee = emp;
        }
      }
      highestPaidList.put(dept.getName(), tempEmployee);
    }

    return highestPaidList;
  }

}
