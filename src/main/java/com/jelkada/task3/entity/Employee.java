package com.jelkada.task3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="employee")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
// @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  // @EqualsAndHashCode.Include
  private int id;

  @Column(name="name")
  @NonNull
  @EqualsAndHashCode.Include
  private String name;

  @Column(name="email", unique = true)
//  @EqualsAndHashCode.Include
  @NonNull
  private String email;

  @Column(name="salary")
  @NonNull
  private Double salary;

  @Column(name="age")
  @NonNull
  private Integer age;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="address_id")
  @NonNull
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
  @JsonIgnore
  private List<Project> projects;

  public  void addProject(Project theProject) {
    if (projects == null) {
      projects = new ArrayList<>();
    } else if (projects.size() >= 3) {
      throw new IllegalStateException("An employee cannot work on more than 3 projects.");
    }

    projects.add(theProject);
  }

  public void removeProject(Project theProject) {
    projects.remove(theProject);

    theProject.getEmployees().remove(this);
  }
}
