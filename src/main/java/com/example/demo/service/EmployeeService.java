package com.example.demo.service;

import com.example.demo.dao.EmployeeJpaRepository;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

  @Autowired
  EmployeeJpaRepository employeeJpaRepository;

  @Override
  public List<Employee> getAllEmployees() {
    return employeeJpaRepository.findAll();
  }

  @Override
  public Employee findEmployeeById(int id) {
    Optional<Employee> emp = employeeJpaRepository.findById(id);

    Employee resultEmployee = null;
    if (emp.isPresent()) {
      resultEmployee = emp.get();
    }

    return resultEmployee;
  }
}
