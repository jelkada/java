package com.jelkada.task3.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="employee")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @Column(name="name")
  private String name;

  @Column(name="email")
  private String email;

  @Column(name="salary")
  private double salary;

  @Column(name="age")
  private int age;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="address_id")
  private Address address;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
  @JoinColumn(name="department_id")
  private Department department;

  @ManyToMany(
      fetch = FetchType.EAGER,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
  )
  @JoinTable(
      name="employee_project",
      joinColumns = @JoinColumn(name="employee_id"),
      inverseJoinColumns = @JoinColumn(name="project_id")
  )
  private List<Project> projects;


  public Employee() {
  }

  public Employee(String name, String email, double salary, int age) {
    this.name = name;
    this.email = email;
    this.salary = salary;
    this.age = age;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", salary=" + salary +
        ", age=" + age +
        ", address=" + address +
        '}';
  }

  public  void addProject(Project theProject) {
    if (projects == null) {
      projects = new ArrayList<>();
    }

    projects.add(theProject);
  }

}
