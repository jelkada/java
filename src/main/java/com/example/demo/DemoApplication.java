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

			List<Employee> empList = employeeService.getAllEmployees();
			System.out.println("\nemployeeService.getAllEmployees()");
			printEmployees(empList);

			Employee[] empArray = empList.toArray(new Employee[0]);
			System.out.println("\nThe employees array: ");
			printEmployeesArray(empArray);

			System.out.println("\ngetHighestPaidEmpByDept(empArray):");
			getHighestPaidEmpByDept(empArray);

			System.out.println("\nsortEmpBySalaryAge(empArray):");
			printEmployeesArray(sortEmpBySalaryAge(empArray));

			System.out.println("\ngetDuplicateEmpNames(empArray):" + getDuplicateEmpNames(empArray));

			System.out.println("\ngetEmployeesPerDept(empArray):" + getEmployeesPerDept(empArray));

			System.out.println("\ngetHighestSalaryEmployee(empArray): $" + String.format("%,.2f", getHighestSalaryEmployee(empArray)));

			System.out.println("\ncheckPalimdromeIds(empList):");
			checkPalimdromeIds(empList);

			System.out.println("\nshiftRightEmployees(empList, 3):");
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

	private void printEmployeesArray(Employee[] empArray) {
		for (Employee emp: empArray) {
			System.out.println(emp);
		}
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
			String empId = Integer.toString(emp.getId());
			System.out.println( +emp.getId() + " - " + empId.contentEquals(new StringBuilder(empId).reverse()) );
		}
	}

	private double getHighestSalaryEmployee(Employee[] empArray) {
		double highestSalary = 0;
		for (Employee emp: empArray) {
			highestSalary = Math.max(emp.getSalary(), highestSalary);
		}

		return highestSalary;
	}

	private Map<String, Integer> getEmployeesPerDept(Employee[] empArray) {
		Map<String, Integer> deptMap = new HashMap<>();
		for (Employee emp : empArray) {
			String dept = emp.getDepartment();
			deptMap.put(dept, deptMap.containsKey(dept) ? deptMap.get(dept) + 1 : 1);
		}

		return deptMap;
	}

	private Set<String> getDuplicateEmpNames(Employee[] empArray) {
		Set<String> uniqueNames = new HashSet<>();
		Set<String> duplicateNames = new HashSet<>();

		for (Employee emp: empArray) {
			if (!uniqueNames.add(emp.getName())) {
				duplicateNames.add(emp.getName());
			}
		}

		return duplicateNames;
	}

	private void printEmployees(List<Employee> empList) {
		for (Employee emp: empList) {
			System.out.println(emp);
		}
	}

	private Employee[] sortEmpBySalaryAge(Employee[] empArray) {

		Arrays.sort(empArray, (emp1, emp2) ->  {
			int compareSalary = Double.compare(emp2.getSalary(), emp1.getSalary());
			return compareSalary != 0 ? compareSalary : Double.compare(emp1.getAge(), emp2.getAge());
		});

		return empArray;
	}

	private void getHighestPaidEmpByDept(Employee[] empArray) {
		Map<String, Employee> highestPaidByDept = new HashMap<>();

		for(Employee emp: empArray) {
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

//	private List<Employee> getEmployeeList() {
//		List<Employee> employeeList = new ArrayList<>();
//		employeeList.add(new Employee(100, "Charlie", 66, 9000.90, "Engineering"));
//		employeeList.add(new Employee(101, "Jim", 40, 990000, "Engineering"));
//		employeeList.add(new Employee(102, "Charlie", 78, 70000, "Marketing"));
//		employeeList.add(new Employee(103, "John111", 25, 99999.99, "Marketing"));
//		employeeList.add(new Employee(1000, "Jim", 55, 68500.20, "Engineering"));
//		employeeList.add(new Employee(1001, "David", 35, 85000, "Marketing"));
//		employeeList.add(new Employee(1002, "Jena", 99, 5000.01, "HR"));
//		employeeList.add(new Employee(1003, "Alice", 19, 85000, "HR"));
//
//		return employeeList;
//	}
}
