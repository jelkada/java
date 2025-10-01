package com.jelkada.task3.service;

import com.jelkada.task3.entity.Department;
import com.jelkada.task3.entity.Employee;
import com.jelkada.task3.entity.Project;
import com.jelkada.task3.entity.Task;
import com.jelkada.task3.repository.DepartmentRepository;
import com.jelkada.task3.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

  private final ProjectRepository projectRepository;

  public ProjectService(ProjectRepository theProjectRepository) {
    projectRepository = theProjectRepository;
  }

  public List<Project> getAllProjects() {
    return projectRepository.findAll();
  }

  public Project getProjectById(int projectId) {
    return projectRepository.findById(projectId)
        .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
  }

  @Transactional
  public void deleteProject(int projectId) {
    Project theProject = getProjectById(projectId);

    // break the association between the project and the department (both ways)
    theProject.getDepartment().getProjects().remove(theProject);
    theProject.setDepartment(null);

    // break the associations between the project and all employees
    theProject.getEmployees().forEach(emp -> emp.getProjects().remove(theProject));
    theProject.getEmployees().clear();

    // Not needed because of CascadeType.ALL
    // theProject.getTasks().forEach(task -> task.setProject(null));
    // theProject.getTasks().forEach(task -> task.setEmployee(null));
    // theProject.getTasks().clear();

    projectRepository.delete(theProject);
  }

  public List<Employee> getEmployeesByProjectId(int projectId) {
    Project theProject = getProjectById(projectId);
    return theProject.getEmployees();
  }
}
