package com.jelkada.task3.DAO;

import com.jelkada.task3.entity.Department;
import com.jelkada.task3.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
  // List instead of Optional in case we insert multiple times
  List<Department> findByName(String name);
}
