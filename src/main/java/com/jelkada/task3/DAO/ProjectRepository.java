package com.jelkada.task3.DAO;

import com.jelkada.task3.entity.Employee;
import com.jelkada.task3.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
  @Query(value=
      "SELECT * " +
      "FROM employee " +
      "JOIN employee_project ON employee.id = employee_project.employee_id " +
      "WHERE employee_project.project_id = :data;", nativeQuery = true)
  List<Employee> findProjectEmployeesByProjectId(@Param("data") int projectId);

  void deleteById(int projectId);
}
