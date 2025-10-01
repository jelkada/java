package com.jelkada.task3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jelkada.task3.entity.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//  @Query(value="SELECT name FROM employee JOIN department ON employee.id = department.id", nativeQuery = true)
//  public List<String> someFunction();

  @Query(value="SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e2.salary) FROM Employee e2 WHERE e2.department = e.department)")
  public List<Employee> getHighestEmployeeSalaryByDepartment();

  List<Employee> findByDepartmentId(int departmentId);
  List<Employee> findByDepartmentName(String departmentName);
}
