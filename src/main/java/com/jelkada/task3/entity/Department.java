package com.jelkada.task3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="department")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
  @JsonIgnore
  private List<Employee> employees;

//  In a bidirectional relationship the owning side is the one responsible for managing the foreign key (department_id)
//  And The @JoinColumn must be on the owning side — otherwise, Hibernate won't know where to insert/update the FK.
  // This would be the setup for unidirectional - if Project did not have @ManyToOne
//  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//  @JoinColumn(name = "department_id") // FK in Project table
  @OneToMany(
      mappedBy="department",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Project> projects;

  public  void addProject(Project theProject) {
    if (projects == null) {
      projects = new ArrayList<>();
    }

    projects.add(theProject);
  }
}
