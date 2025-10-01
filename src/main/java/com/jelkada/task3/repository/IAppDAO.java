package com.jelkada.task3.repository;

import com.jelkada.task3.entity.Department;
import com.jelkada.task3.entity.Employee;
import com.jelkada.task3.entity.Project;

import java.util.List;

public interface IAppDAO {

  Employee findEmployeeById(int id);
  Department findDepartmentById(int id);
  Project findProjectById(int id);

  void saveEmployee (Employee theEmployee);
  void saveDepartment (Department theDepartment);

  void assignEmployeeToDepartment(int empId, String deptName);
  void assignProjectsToEmployee(int empId, int[] projectIds);

  List<Employee> findEmployeesByDepartmentId(int deptId);
  List<Employee> findEmployeesByDepartmentName(String deptName);

  List<Project> findAllProjectsByDepartmentId(int deptId);

  List<Employee> findEmployeesByProjectId(int projectId);

  void deleteProjectById(int projectId);
  void deleteTaskById(int taskId);
}
