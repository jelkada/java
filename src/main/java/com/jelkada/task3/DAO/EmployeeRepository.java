package com.jelkada.task3.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jelkada.task3.entity.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//  @Query(value="SELECT name FROM employee JOIN department ON employee.id = department.id", nativeQuery = true)
//  public List<String> someFunction();

  List<Employee> findByDepartmentId(int departmentId);
  List<Employee> findByDepartmentName(String departmentName);
}
