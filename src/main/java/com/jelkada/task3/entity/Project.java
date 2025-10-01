package com.jelkada.task3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="project")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @Column(name="title")
  private String title;

  @Column(name="budget")
  private double budget;

  @ManyToOne
  @JoinColumn(name = "department_id") // FK column
  @JsonIgnore
  private Department department;

  @ManyToMany(
      mappedBy = "projects",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
  )
  @JsonIgnore
  private List<Employee> employees;

  @OneToMany(
      mappedBy = "project",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private List<Task> tasks;

  public  void addEmployee(Employee theEmployee) {
    if (employees == null) {
      employees = new ArrayList<>();
    }

    employees.add(theEmployee);
  }

  public  void addTask(Task theTask) {
    if (tasks == null) {
      tasks = new ArrayList<>();
    }

    tasks.add(theTask);
  }

}
