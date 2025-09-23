package com.jelkada.task3.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="department")
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @Column(name="name")
  private String name;

  @Column(name="location")
  private String location;

  @OneToMany(
      mappedBy="department",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
  )
  private List<Employee> employees;

//  This is the setup if we had Unidirectional relationship
//  In a bidirectional relationship - The owning side is the one responsible for managing the foreign key (department_id)
//  And The @JoinColumn must be on the owning side — otherwise, Hibernate won't know where to insert/update the FK.
//  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//  @JoinColumn(name = "department_id") // FK in Project table
  @OneToMany(
      mappedBy="department",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL)
  private List<Project> projects;

  public Department() {
  }

  public Department(String name, String location) {
    this.name = name;
    this.location = location;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

  public  void addProject(Project theProject) {
    if (projects == null) {
      projects = new ArrayList<>();
    }

    projects.add(theProject);
  }

  @Override
  public String toString() {
    return "Department{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", location='" + location + '\'' +
        '}';
  }

}
