package com.jelkada.task3.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.jelkada.task3.entity.Department;
import com.jelkada.task3.entity.Employee;
import com.jelkada.task3.entity.Task;
import com.jelkada.task3.service.DepartmentService;
import com.jelkada.task3.service.EmployeeService;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

  private final EmployeeService employeeService;
  private final DepartmentService departmentService;

  public EmployeeController(EmployeeService theEmployeeService, DepartmentService theDepartmentService) {
    employeeService = theEmployeeService;
    departmentService = theDepartmentService;
  }

  // get the list of all employees
  @GetMapping
  public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  // get employee by a given employee id
  @GetMapping(path = "/{id}")
  public Employee getEmployeeById(@PathVariable("id") int employeeId) {
    return employeeService.getEmployeeById(employeeId);
  }

  // create a new employee
  @PostMapping
  public ResponseEntity<String> createEmployee(@RequestBody Employee emp) {
    Employee savedEmployee = employeeService.saveEmployee(emp);

    return ResponseEntity.status(201).body("Employee Created successfully");
  }

  // update an employee
  @PutMapping(path = "/{id}")
  public Employee updateEmployee(@PathVariable("id") int employeeId, @RequestBody Employee emp) throws JsonMappingException {
    return employeeService.updateEmployee(employeeId, emp);
  }

  // partial update of an employee
  @PatchMapping(path = "/{id}")
  public Employee patchEmployee(@PathVariable("id") int employeeId, @RequestBody Map<String, Object> emp) throws JsonMappingException {
    return employeeService.patchEmployee(employeeId, emp);
  }

  // delete an employee by employee id
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") int employeeId) {
    employeeService.deleteEmployee(employeeId);
    return ResponseEntity.status(200).body("Employee was deleted successfully");
  }

  // get a list all employees for a given department
  @GetMapping(path = "/department/{id}")
  public List<Employee> getEmployeeByDepartmentId(@PathVariable("id") int departmentId) {
    return employeeService.getEmployeeByDepartmentId(departmentId);
  }

  // get all tasks for a given employee
  @GetMapping(path = "/{id}/tasks")
  public List<Task> getTasksEmployeeById(@PathVariable("id") int employeeId) {
    return employeeService.getTasksEmployeeById(employeeId);
  }

  // assign department to an employee
  @PutMapping("/{id}/department/{departmentId}")
  public ResponseEntity<?> assignDepartmentToEmployee(@PathVariable("id") int employeeId, @PathVariable int departmentId) {
    Employee savedEmployee = employeeService.assignDepartmentToEmployee(employeeId, departmentId);
    System.out.println("\n\n\n savedEmployee: " + savedEmployee);
    Map<String, Object> resp = new HashMap<>();
    resp.put("employee", savedEmployee);
    return ResponseEntity.ok(resp);
  }

  // highest paid employee in all departments
  @GetMapping(path = "/highest-paid")
  public Map.Entry<String, Double> highestPaidPerDepartment() {
    List<Employee> empList = getAllEmployees();

    double highestSalary = 0.0;
    String highestSalaryName = "";
    for (Employee emp : empList) {
      if (emp.getSalary() > highestSalary) {
        highestSalary = emp.getSalary();
        highestSalaryName = emp.getName();
      }
    }

    return new AbstractMap.SimpleEntry<>(highestSalaryName, highestSalary);
  }

  // highest paid employee in all departments
  @GetMapping(path = "/highest-paid-query")
  public List<Employee> highestPaidEmployeeByDepartment() {
    return employeeService.getHighestEmployeeSalaryByDepartment();
  }

  // highest paid employee in all departments
  @GetMapping(path = "/more-than-two-projects")
  public List<Employee> getEmployeeWorkingOnMoreThanTwoProjects() {
    return employeeService.getEmployeeWorkingOnMoreThanTwoProjects();
  }
}