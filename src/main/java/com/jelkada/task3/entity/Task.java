package com.jelkada.task3.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="task")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @Column(name="description")
  private String description;

  @Column(name="deadline")
  private LocalDate deadline;

  @ManyToOne
  @JoinColumn(name="employee_id")
  private Employee employee;

  @ManyToOne
  @JoinColumn(name="project_id")
  private Project project;

  public Task() {
  }

  public Task(String description, LocalDate deadline) {
    this.description = description;
    this.deadline = deadline;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDate deadline) {
    this.deadline = deadline;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  @Override
  public String toString() {
    return "Task{" +
        "id=" + id +
        ", description='" + description + '\'' +
        ", deadline=" + deadline +
        '}';
  }
}
