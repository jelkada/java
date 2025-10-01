package com.jelkada.task3.service;

import com.jelkada.task3.entity.Department;
import com.jelkada.task3.entity.Employee;
import com.jelkada.task3.entity.Project;
import com.jelkada.task3.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentService {

  private final DepartmentRepository departmentRepository;

  public DepartmentService(DepartmentRepository theDepartmentRepository) {
    departmentRepository = theDepartmentRepository;
  }

  public List<Department> getAllDepartments() {
    return departmentRepository.findAll();
  }

    public Department getDepartmentById(int departmentId) {
      return departmentRepository.findById(departmentId)
        .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));
  }

  public List<Employee> getEmployeesByDepartmentId(int departmentId) {
    Department theDepartment = getDepartmentById(departmentId);
    return theDepartment.getEmployees();
  }

  public List<Project> getProjectsByDepartmentId(int departmentId) {
    Department theDepartment = getDepartmentById(departmentId);
    return theDepartment.getProjects();
  }

  @Transactional
  public Department updateDepartment(int departmentId, Department updatedDepartment) {
    Department dbDepartment = getDepartmentById(departmentId);

    dbDepartment.setName(updatedDepartment.getName());
    dbDepartment.setLocation(updatedDepartment.getLocation());

    return departmentRepository.save(dbDepartment);
  }

  @Transactional
  public Department patchDepartment(int departmentId, Map<String, Object> partialDepartment) {
    Department dbDepartment = getDepartmentById(departmentId);

    if (partialDepartment.containsKey("name")) {
      dbDepartment.setName((String) partialDepartment.get("name"));
    }
    if (partialDepartment.containsKey("location")) {
      dbDepartment.setLocation((String) partialDepartment.get("location"));
    }

    return departmentRepository.save(dbDepartment);
  }

  @Transactional
  public void deleteDepartmentById(int deptId) {
    Department theDepartment = getDepartmentById(deptId);

    // break the association between the employees and the department
    for (Employee emp: theDepartment.getEmployees()) {
      emp.setDepartment(null);
    }
    // break the association between the employees and the project
    for (Project proj: theDepartment.getProjects()) {
      proj.setDepartment(null);
    }

    departmentRepository.delete(theDepartment);
  }
}
