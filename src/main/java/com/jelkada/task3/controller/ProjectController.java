package com.jelkada.task3.controller;

import com.jelkada.task3.entity.Department;
import com.jelkada.task3.entity.Employee;
import com.jelkada.task3.entity.Project;
import com.jelkada.task3.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/projects")
public class ProjectController {

  private final ProjectService projectService;

  public ProjectController(ProjectService theProjectService) {
    projectService = theProjectService;
  }

  @GetMapping
  public List<Project> getAllProjects() {
    return projectService.getAllProjects();
  }

  @GetMapping(path = "/{id}")
  public Project getProjectById(@PathVariable("id") int projectId) {
    return projectService.getProjectById(projectId);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteProject(@PathVariable("id") int projectId) {
    projectService.deleteProject(projectId);
  }

  @GetMapping(path = "/{id}/employees")
  public List<Employee> getEmployeesByDepartmentId(@PathVariable("id") int projectId) {
    return projectService.getEmployeesByProjectId(projectId);
  }

}
