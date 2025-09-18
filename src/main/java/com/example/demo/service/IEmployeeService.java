package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Employee;

public interface IEmployeeService {
  List<Employee> getAllEmployees();
  Employee findEmployeeById(int id);
}
