package com.jelkada.task3;

import com.jelkada.task3.DAO.AppDAO;
import com.jelkada.task3.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Task3Application {

	public static void main(String[] args) {
		SpringApplication.run(Task3Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

			addEmployeesWithAddress(appDAO);

			addEmployeeWithAddressAndDepartment(appDAO);

			addDepartment(appDAO);

			assignEmployeeToDepartment(appDAO);

			addProjectsToDepartment(appDAO);

			assignProjectsToEmployee(appDAO);

			addTasksToEmployeeWithProject(appDAO);

			findAllEmployeesByDepartmentId(appDAO);
			findAllEmployeesByDepartmentName(appDAO);

			findAllProjectsByDepartmentId(appDAO);

			findAllEmployeesByProjectId(appDAO);

			deleteProjectById(appDAO);

			deleteTaskById(appDAO);
		};
	}

	private void addEmployeesWithAddress(AppDAO appDAO) {
		Employee tempEmployee1 = new Employee("David Emp1", "david" + getRandomNum() +"@gmail.com", 72000.25, 45);
		Address tempAddress1 = new Address("36 Combe Rd.", "Toronto", "ON", "M3H 2N2");
		tempEmployee1.setAddress(tempAddress1);

		appDAO.saveEmployee(tempEmployee1);

		Employee tempEmployee2 = new Employee("Sarah Emp3", "sarah_emp" + getRandomNum() + "@yahoo.com", 72000.25, 45);
		Address tempAddress2 = new Address("119 Avenue Rd. #24", "Vancouver", "BC", "K3L 1G2");
		tempEmployee2.setAddress(tempAddress2);

		appDAO.saveEmployee(tempEmployee2);
	}

	private void addEmployeeWithAddressAndDepartment(AppDAO appDAO) {
		Employee tempEmployee = new Employee("Jimmy Johnes", "jim" + getRandomNum() + "@gmail.com", 72000.25, 45);
		Address tempAddress = new Address("111 Hove Ave.", "Toronto", "ON", "M9P 1N3");
		Department tempDepartment = new Department("Sales", "Ontario");

		tempEmployee.setAddress(tempAddress);
		tempEmployee.setDepartment(tempDepartment);

		appDAO.saveEmployee(tempEmployee);
	}

	private void addDepartment(AppDAO appDAO) {
		Department tempDepartment = new Department("Dev Ops", "Quebec");

		appDAO.saveDepartment(tempDepartment);
	}

	private void assignEmployeeToDepartment(AppDAO appDAO) {
		appDAO.assignEmployeeToDepartment(1, "Sales");
	}

	private void addProjectsToDepartment(AppDAO appDAO) {
		Project newProject1 = new Project("Project AA", 198000);
		Project newProject2 = new Project("Project BB", 22890.99);

		Department theDepartment = appDAO.findDepartmentById(2);

		newProject1.setDepartment(theDepartment);
		newProject2.setDepartment(theDepartment);

		theDepartment.setProjects(List.of(newProject1, newProject2));

		appDAO.saveDepartment(theDepartment);
	}

	private void assignProjectsToEmployee(AppDAO appDAO) {
		// appDAO.assignProjectsToEmployee(1, new int[]{1, 2});
		Employee tempEmployee = new Employee("NEW EMP 111", "jim" + getRandomNum() + "@gmail.com", 7000, 22);
		// Address tempAddress = new Address("777 Hove Ave.", "Toronto", "ON", "M9P 1N3");

		Project newProject1 = new Project("Project NEW1", 198000);
		Project newProject2 = new Project("Project NEW2", 22890.99);

		tempEmployee.setProjects(List.of(newProject1, newProject2));
		appDAO.saveEmployee(tempEmployee);
	}

	private void addTasksToEmployeeWithProject(AppDAO appDAO) {
		Employee theEmployee = appDAO.findEmployeeById(2);
		Project theProject = theEmployee.getProjects().getFirst();

		Task task1 = new Task("this is task ABC", LocalDate.of(2026, 9, 30));
		Task task2 = new Task("this is task DEF", LocalDate.of(2025, 11, 21));

		task1.setProject(theProject);
		task1.setEmployee(theEmployee);

		task2.setProject(theProject);
		task2.setEmployee(theEmployee);

		theProject.addTask(task1);
		theProject.addTask(task2);

		appDAO.saveEmployee(theEmployee);
	}

	private void findAllEmployeesByDepartmentId(AppDAO appDAO) {
		List<Employee> employeeList = appDAO.findEmployeesByDepartmentId(2);

		System.out.println("All employees of Marketing: ");
		for (Employee emp: employeeList) {
			System.out.println("- " + emp.getName());
		}
	}

	private void findAllEmployeesByDepartmentName(AppDAO appDAO) {
		List<Employee> employeeList = appDAO.findEmployeesByDepartmentName("Sales");

		System.out.println("All employees of Sales: ");
		for (Employee emp: employeeList) {
			System.out.println("- " + emp.getName());
		}
	}

	private void findAllProjectsByDepartmentId(AppDAO appDAO) {
		List<Project> projectList = appDAO.findAllProjectsByDepartmentId(2);

		System.out.println("All projects in Marketing (id=2)");
		projectList.forEach(project -> System.out.println(project.getTitle()));
	}

	public void findAllEmployeesByProjectId(AppDAO appDAO) {
		System.out.println("All employees in project (id=1)");
		List<Employee> employeeList = appDAO.findEmployeesByProjectId(2);

		System.out.println("All employees in project id: ");
		employeeList.forEach(emp -> System.out.println(emp.getName()));
	}

	private void deleteProjectById(AppDAO appDAO) {
		appDAO.deleteProjectById(1);
	}

	private void deleteTaskById(AppDAO appDAO) {
		appDAO.deleteTaskById(3);
	}

	// use to generate random email (email field UNIQUE in SQL Script)
	private String getRandomNum() {
		Random random = new Random();
		int randomNumber = random.nextInt(10000);
		return String.format("%04d", randomNumber);
	}
}
