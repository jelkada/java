package com.example.demo;

import com.example.demo.dao.AppDAO;
import com.example.demo.entity.*;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	EmployeeService employeeService;

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {
			//new version
			List<Employee> empList = employeeService.getAllEmployees();
			System.out.println("\nemployeeService.getAllEmployees()");
			printEmployees(empList);

			// old version
			// System.out.println(appDAO.getOneRow(2));

			System.out.println("\nThe employees list:");
			printEmployees(empList);

			System.out.println("\ngetHighestPaidEmpByDept(empList):");
			getHighestPaidEmpByDept(empList);

			System.out.println("\nsortEmpBySalaryAge(empList):");
			printEmployees(sortEmpBySalaryAge(empList));

			System.out.println("\ngetDuplicateEmpNames(empList):");
			getDuplicateEmpNames(empList);

			System.out.println("\n\ngetEmployeesPerDept(empList):");
			getEmployeesPerDept(empList);

			System.out.println("\ngetHighestSalaryEmployee(empList): $" + String.format("%,.2f", getHighestSalaryEmployee(empList)));

			System.out.println("\ncheckPalimdromeIds(empList):");
			checkPalimdromeIds(empList);

			System.out.println("\nshifyRightEmployees(empList, 3):");
			printEmployees(shiftRightEmployees(empList, 3));

			System.out.println("\nVehicle data:");
			Vehicle[] vehicles = {
					new Car("Toyota", "Camry", 2020, 4),
					new Bike("Royal Enfield", "Classic 350", 2019, true),
					new Truck("Volvo", "FH16", 2018, 20)
			};
			printCarsInfo(vehicles);
		};
	}

	private void printCarsInfo(Vehicle[] vehicles) {
		for (Vehicle vehicle: vehicles) {
			System.out.println("--------------------");
			vehicle.start();
			vehicle.stop();
			vehicle.getDetails();
			System.out.println("Cost: " + String.format("%,.2f", vehicle.calculateServiceCost()));
		}
	}

	private List<Employee> shiftRightEmployees(List<Employee> empList, int k) {
		for (int i = 0; i < k; i++) {
			empList.addFirst(empList.removeLast());
		}

		return empList;
	}

	private void checkPalimdromeIds(List<Employee> empList) {
		for (Employee emp: empList) {
			System.out.println( +emp.getId() + " - " + isPalindrome(Integer.toString(emp.getId())));
		}
	}

	private boolean isPalindrome(String id) {
		int len = id.length();
		int mid = (int) Math.floor(len /2);

		for (int i = 0; i < mid; i++) {
			if (id.charAt(i) != id.charAt(len - 1 - i)) {
				return false;
			}
		}

		return true;
	}

	private double getHighestSalaryEmployee(List<Employee> empList) {
		double highestSalary = 0;
		for (Employee emp: empList) {
			highestSalary = Math.max(emp.getSalary(), highestSalary);
		}

		return highestSalary;
	}

	private void getEmployeesPerDept(List<Employee> empList) {
		Map<String, List<Employee>> deptMap = new HashMap<>();
		for (Employee emp : empList) {
			String dept = emp.getDepartment();
			List<Employee> employees = deptMap.getOrDefault(dept, new ArrayList<>());
			employees.add(emp);
			deptMap.put(dept, employees);
		}

		System.out.println("Number of employees per department: ");
		for (Map.Entry<String, List<Employee>> entry : deptMap.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue().size() + " employees.");
		}
	}

	private void getDuplicateEmpNames(List<Employee> empList) {
		Set<String> uniqueNames = new HashSet<>();
		Set<String> duplicateNames = new HashSet<>();

		System.out.print("Duplicate names: ");
//		for (Employee emp: empList) {
//			if (!uniqueNames.add(emp.getName())) {
//				System.out.print(emp.getName() + " ");
//			}
//		}

		for (Employee emp: empList) {
			if (!uniqueNames.add(emp.getName())) {
				duplicateNames.add(emp.getName());
			}
		}
		for (String name: duplicateNames) {
			System.out.print(" " +name);
		}


//		Map<String, Integer> namesMap = new HashMap<>();

//		for (Employee emp: empList) {
//			String name = emp.getName();
//			namesMap.put(name, namesMap.containsKey(name) ? namesMap.get(name)+1 : 1);
//		}
//
//		for (Map.Entry<String, Integer> entry : namesMap.entrySet()) {
//			if (entry.getValue() > 1) {
//				System.out.print(" " + entry.getKey());
//			}
//		}
	}

	public void printEmployees(List<Employee> empList) {
		for (Employee emp: empList) {
			System.out.println(emp);
		}
	}

	public List<Employee> sortEmpBySalaryAge(List<Employee> empList) {
		List<Employee> sortedEmpList = new ArrayList<>(empList);
		sortedEmpList.sort((emp1, emp2) ->  {
			int compareSalary = Double.compare(emp2.getSalary(), emp1.getSalary());
			return compareSalary != 0 ? compareSalary : Double.compare(emp1.getAge(), emp2.getAge());
		});

		return sortedEmpList;
	}

	public void getHighestPaidEmpByDept(List<Employee> empList) {
		Map<String, Employee> highestPaidByDept = new HashMap<>();

		for(Employee emp: empList) {
			String dept = emp.getDepartment();
			double salary = emp.getSalary();
			if (!highestPaidByDept.containsKey(dept) || emp.getSalary() > highestPaidByDept.get(dept).getSalary()) {
				highestPaidByDept.put(dept, emp);
			}
		}

		for (Map.Entry<String, Employee> entry : highestPaidByDept.entrySet()) {
			System.out.println("Department: " + entry.getKey() + ", Employee: " + entry.getValue().getName() + ", Salary: $" + String.format("%,.2f", entry.getValue().getSalary()));
		}
	}

	public List<Employee> getEmployeeList() {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Employee(100, "Charlie", 66, 9000.90, "Engineering"));
		employeeList.add(new Employee(101, "Jim", 40, 990000, "Engineering"));
		employeeList.add(new Employee(102, "Charlie", 78, 70000, "Marketing"));
		employeeList.add(new Employee(103, "John111", 25, 99999.99, "Marketing"));
		employeeList.add(new Employee(1000, "Jim", 55, 68500.20, "Engineering"));
		employeeList.add(new Employee(1001, "David", 35, 85000, "Marketing"));
		employeeList.add(new Employee(1002, "Jena", 99, 5000.01, "HR"));
		employeeList.add(new Employee(1003, "Alice", 19, 85000, "HR"));

		return employeeList;
	}
}
