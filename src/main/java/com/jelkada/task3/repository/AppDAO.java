package com.jelkada.task3.repository;

import com.jelkada.task3.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class AppDAO implements IAppDAO {

  private EmployeeRepository employeeRepository;
  private AddressRepository addressRepository;
  private DepartmentRepository departmentRepository;
  private ProjectRepository projectRepository;
  private TaskRepository taskRepository;
  private EntityManager entityManager;

  public AppDAO(EntityManager theEntityManager, EmployeeRepository theEmployeeRepository,
                AddressRepository theAddressRepository, DepartmentRepository theDepartmentRepository,
                ProjectRepository theProjectRepository, TaskRepository theTaskRepository) {
    entityManager = theEntityManager;
    employeeRepository = theEmployeeRepository;
    addressRepository = theAddressRepository;
    departmentRepository = theDepartmentRepository;
    projectRepository = theProjectRepository;
    taskRepository = theTaskRepository;
  }

  @Override
  public Employee findEmployeeById(int id) {
    Optional<Employee> tempEmployee = employeeRepository.findById(id);

    Employee theEmployee = null;

    if (tempEmployee.isPresent()) {
      theEmployee = tempEmployee.get();
    }

    return theEmployee;
  }

  @Override
  public Department findDepartmentById(int id) {
    Optional<Department> tempDepartment = departmentRepository.findById(id);

    Department theDepartment = null;

    if (tempDepartment.isPresent()) {
      theDepartment = tempDepartment.get();
    }

    return theDepartment;
  }

  @Override
  public Project findProjectById(int id) {
    Optional<Project> tempProject = projectRepository.findById(id);

    Project theProject = null;

    if (tempProject.isPresent()) {
      theProject = tempProject.get();
    }

    return theProject;
  }


  @Override
  @Transactional
  public void saveEmployee(Employee theEmployee) {
    // entityManager.persist(theEmployee);
    employeeRepository.save(theEmployee);
  }

  @Override
  @Transactional
  public void saveDepartment(Department theDepartment) {
    //entityManager.merge(theDepartment);
    departmentRepository.save(theDepartment);
  }

  @Override
  @Transactional
  public void assignEmployeeToDepartment(int empId, String deptName) {
    Employee theEmployee = findEmployeeById(1);
    Department theDepartment = departmentRepository.findByName(deptName).getFirst();

    theEmployee.setDepartment(theDepartment);

    saveEmployee(theEmployee);
  }

  @Override
  @Transactional
  public void assignProjectsToEmployee(int empId, int[] projectIds) {
    Employee theEmployee = findEmployeeById(empId);

    for (int projectId : projectIds) {
      Project tempProject = findProjectById(projectId);
      theEmployee.addProject(tempProject);
    }

    try {
      employeeRepository.save(theEmployee);
    } catch (DataIntegrityViolationException exc) {
      throw new RuntimeException("Failed to save employee: " + exc.getMessage());
    }
  }

  @Override
  public List<Employee> findEmployeesByDepartmentId(int deptId) {
    if (!departmentRepository.existsById(deptId)) {
      throw new EntityNotFoundException("Department not found: ID = " + deptId);
    }

    return employeeRepository.findByDepartmentId(deptId);
  }

  @Override
  public List<Employee> findEmployeesByDepartmentName(String deptName) {
    List<Department> deptList = departmentRepository.findByName(deptName);
    if (deptList.isEmpty()) {
      throw new EntityNotFoundException("Department not found: " + deptName);
    }

    return employeeRepository.findByDepartmentName(deptName);
  }

  @Override
  public List<Project> findAllProjectsByDepartmentId(int deptId) {
    if (!departmentRepository.existsById(deptId)) {
      throw new EntityNotFoundException("Department not found: ID = " + deptId);
    }

    Department theDepartment = findDepartmentById(deptId);

    return theDepartment.getProjects();
  }

  @Override
  public List<Employee> findEmployeesByProjectId(int projectId) {

    return projectRepository.findProjectEmployeesByProjectId(projectId);

    // this clearly will cause the error: failed to lazily initialize a collection
    // Project theProject = findProjectById(projectId);
    // return theProject.getEmployees();
  }

  @Override
  @Transactional
  public void deleteProjectById(int projectId) {
    Project theProject = findProjectById(projectId);

    // remove associations with employees() {
    for (Employee emp : theProject.getEmployees()) {
      emp.getProjects().remove(theProject);
    }

    // remove associations with tasks {
    for (Task task : theProject.getTasks()) {
      task.setProject(null);
    }

    // remove the association of the project with department
    theProject.setDepartment(null);

    // clear project side too
    theProject.getEmployees().clear();

    projectRepository.delete(theProject);
  }

  @Override
  @Transactional
  public void deleteTaskById(int taskId) {
    Optional<Task> tempTask = taskRepository.findById(taskId);

    Task theTask = null;
    if (tempTask.isPresent()) {
      theTask = tempTask.get();
    } else {
      throw new RuntimeException("Task is not found: " + taskId);
    }

    theTask.getProject().getTasks().remove(theTask);

    theTask.setEmployee(null);
    theTask.setProject(null);

    taskRepository.deleteById(taskId);
  }

}