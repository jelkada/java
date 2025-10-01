package com.jelkada.task3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="task")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
  @JsonIgnore
  private Employee employee;

  @ManyToOne
  @JoinColumn(name="project_id")
  @JsonIgnore
  private Project project;
}
