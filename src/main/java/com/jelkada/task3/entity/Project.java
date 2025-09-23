package com.jelkada.task3.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="project")
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
  private Department department;

  @ManyToMany(
      mappedBy = "projects",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
  )
  private List<Employee> employees;

  @OneToMany(
      mappedBy = "project",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL
  )
  private List<Task> tasks;


  public Project() {
  }

  public Project(String title, double budget) {
    this.title = title;
    this.budget = budget;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public double getBudget() {
    return budget;
  }

  public void setBudget(double budget) {
    this.budget = budget;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  @Override
  public String toString() {
    return "Project{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", budget=" + budget +
        '}';
  }

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
