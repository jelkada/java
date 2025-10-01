package com.jelkada.task3.repository;

import com.jelkada.task3.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
  // List instead of Optional in case we insert multiple times
  List<Department> findByName(String name);
}
