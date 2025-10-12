package com.example.demo.model;

import java.util.Objects;

public class Employee {
  private int empId;
  private String empName;
  private double empSalary;

  public Employee() {}

  public Employee(int empId, String empName, double empSalary) {
    this.empId = empId;
    this.empName = empName;
    this.empSalary = empSalary;
  }

  public int getEmpId() {
    return empId;
  }

  public void setEmpId(int empId) {
    this.empId = empId;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public double getEmpSalary() {
    return empSalary;
  }

  public void setEmpSalary(double empSalary) {
    this.empSalary = empSalary;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "empId=" + empId +
        ", empName='" + empName + '\'' +
        ", empSalary=" + empSalary +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return empId == employee.empId;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(empId);
  }

//  @Override
//  public int hashCode() {
//    return 1; // forces all employees into the same hash bucket
//  }
}
