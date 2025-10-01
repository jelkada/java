package com.jelkada.task3.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jelkada.task3.entity.*;
import com.jelkada.task3.repository.DepartmentRepository;
import com.jelkada.task3.repository.EmployeeRepository;
import com.jelkada.task3.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final DepartmentRepository departmentRepository;
  private final TaskRepository taskRepository;
  private final ObjectMapper objectMapper;

  public EmployeeService(EmployeeRepository theEmployeeRepository,
                         DepartmentRepository theDepartmentRepository,
                         TaskRepository theTaskRepository,
                          ObjectMapper theObjectMapper) {
    employeeRepository = theEmployeeRepository;
    departmentRepository = theDepartmentRepository;
    taskRepository = theTaskRepository;
    objectMapper = theObjectMapper;
  }

  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  public Employee getEmployeeById(int id) {

//    Employee emp1 = employeeRepository.findById(12)
//        .orElseThrow(() -> new RuntimeException("Employee not found with id 12"));
//
//    Employee emp2 = employeeRepository.findById(13)
//        .orElseThrow(() -> new RuntimeException("Employee not found with id 13"));
//
//    boolean equalEmp = emp1.equals(emp2);
//    System.out.println("Compare emp1 and emp2: " + equalEmp);

    return employeeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
  }

  @Transactional
  public Employee saveEmployee(Employee emp) {
    return employeeRepository.save(emp);
  }

  @Transactional
  public Employee updateEmployee(int employeeId, Employee employee) { // throws JsonMappingException
    Employee dbEmployee = getEmployeeById(employeeId);
    dbEmployee.setName(employee.getName());
    dbEmployee.setEmail(employee.getEmail());
    dbEmployee.setSalary(employee.getSalary());
    dbEmployee.setAge(employee.getAge());

    // Address employeeAddress = employee.getAddress();
    // if (employeeAddress != null) {
    // dbEmployee.setAddress(employee.getAddress());
    // }

    // Merge all fields from employee into dbEmployee
    // objectMapper.updateValue(dbEmployee, employee);

    return employeeRepository.save(dbEmployee);
  }

  @Transactional
  public Employee patchEmployee(int employeeId, Map<String, Object> partialEmployee) throws JsonMappingException {
    Employee dbEmployee = getEmployeeById(employeeId);

    if (partialEmployee.containsKey("name")) {
      dbEmployee.setName((String) partialEmployee.get("name"));
    }
    if (partialEmployee.containsKey("email")) {
      dbEmployee.setEmail((String) partialEmployee.get("email"));
    }
    if (partialEmployee.containsKey("salary")) {
      dbEmployee.setSalary(Double.valueOf(partialEmployee.get("salary").toString()));
    }
    if (partialEmployee.containsKey("age")) {
      dbEmployee.setAge(Integer.valueOf(partialEmployee.get("age").toString()));
    }

    if (partialEmployee.containsKey("address")) {
      System.out.println("patchEmployee(): address key exists in the payload");
    } else {
      System.out.println("patchEmployee(): address key DOES NOT exist in the payload");
    }

    return dbEmployee;
  }

  @Transactional
  public void deleteEmployee(int employeeId) {
    Employee theEmployee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

    Department theDepartment = theEmployee.getDepartment();
    if (theDepartment != null) {
      theDepartment.getEmployees().remove(theEmployee);
      theEmployee.setDepartment(null);
    }

    for (Project proj: theEmployee.getProjects()) {
      proj.getEmployees().remove(theEmployee);
    }

    for (Task task: taskRepository.findAll()) {
      if (task.getEmployee() == theEmployee) {
        task.setEmployee(null);
      }
    }

    employeeRepository.delete(theEmployee);
  }

  public List<Employee> getEmployeeByDepartmentId(int departmentId) {
    Department theDepartment = departmentRepository.findById(departmentId)
        .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));

    return theDepartment.getEmployees();
  }

  public List<Task> getTasksEmployeeById(int employeeId) {
    Employee theEmployee = getEmployeeById(employeeId);

    return taskRepository.findByEmployeeId(employeeId);
  }

  public Employee assignDepartmentToEmployee(int employeeId, int departmentId) {
    Employee emp = getEmployeeById(employeeId);
    Department dept = departmentRepository.findById(departmentId)
        .orElseThrow(() -> new RuntimeException("Department id not found: " + departmentId));

    emp.setDepartment(dept);
    return saveEmployee(emp);
  }

  public List<Employee> getHighestEmployeeSalaryByDepartment() {
    return employeeRepository.getHighestEmployeeSalaryByDepartment();
  }

  public List<Employee> getEmployeeWorkingOnMoreThanTwoProjects() {
    List<Employee> employeeList = new ArrayList<>();

    for (Employee emp: getAllEmployees()) {
      if (emp.getProjects().size() > 2) {
        employeeList.add(emp);
      }
    }

    return employeeList;
  }
}
