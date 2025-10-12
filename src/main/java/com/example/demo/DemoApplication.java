package com.example.demo;

import com.example.demo.model.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner() {
		return runner -> {
			checkHashSetDuplicate();
			checkHashMapDuplicate();
			checkCompareStudents();
			measureMockEmployees();
			checkHashMapCount();
			checkHashMapMutability();
			checkTransitivity();
			checkEqualsWithoutHashCode();
			checkHashMapCompositeKey();
			checkSymmetryViolation();
			checkHashSetTreeSet();
		};
	}

	private void checkHashSetDuplicate() {
		Person person1 = new Person(1, "John");
		Person person2 = new Person(2, "Mary");
		Person person3 = new Person(1, "John");

		Set<Person> peopleHashSet = new HashSet<>();
		peopleHashSet.add(person1);
		peopleHashSet.add(person2);
		peopleHashSet.add(person3);

		System.out.println("\n-----------------------------------------------------\n");
		System.out.println("checkHashSetDuplicate(): peopleHashSet.size(): "+ peopleHashSet.size());
		System.out.println("checkHashSetDuplicate(): peopleHashSet: "+ peopleHashSet);
	}

	private void checkHashMapDuplicate() {
		Employee employee1 = new Employee(3, "David", 77000);
		Employee employee3 = new Employee(1, "Josh", 77000);
		Employee employee2 = new Employee(2, "Jane", 55000);

		Map<Employee, String> employeeMap = new HashMap<>();
		employeeMap.put(employee1, "Marketing");
		employeeMap.put(employee2, "HR");
		employeeMap.put(employee3, "Dev Ops");

		System.out.println("\n-----------------------------------------------------\n");
		System.out.println("employeeMap size: " + employeeMap.size());
		for (Map.Entry<Employee, String> entry : employeeMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	private void checkCompareStudents() {
		Student student1 = new Student(1, "John");
		Student student2 = new Student(1, "David");

		System.out.println("\n-----------------------------------------------------\n");
		System.out.println("(student1 == student2): " + (student1 == student2));
		System.out.println("student1.equals(student2): " + student1.equals(student2));
	}

	private void measureMockEmployees() {
		Faker faker = new Faker();
		Set<Employee> employees = new HashSet<>();

		long startTime = System.nanoTime();
		for (int i = 1; i <= 10000; i++) { // compare time with 1,000 vs 10,000 employees
			employees.add(new Employee(
					i,
					faker.name().fullName(),
					faker.number().randomDouble(2, 40000, 120000)
			));
		}
		long endTime = System.nanoTime();

		// Print a few random ones
		employees.stream().limit(10).forEach(System.out::println);
		System.out.println("Total employees: " + employees.size());
		System.out.println("Time taken (normal hash): " + (endTime - startTime) / 1_000_000.0 + " ms");
		// uncomment the hashCode() to see the collision performance (hasCode() is same value, but equals() is false)
	}

	private void checkHashMapCount() {
		Map<Book, Integer> countMap = new HashMap<>();
		Book book1 = new Book("isbn-123", "Learn Java Today!");
		Book book2 = new Book("isbn-456", "Advanced Java");
		Book book3 = new Book("isbn-789", "Angular NgRx");

		countMap.put(book1, countMap.getOrDefault(book1, 0) + 1);
		countMap.put(book2, countMap.getOrDefault(book2, 0) + 1);
		countMap.put(book3, countMap.getOrDefault(book3, 0) + 1);
		countMap.put(book1, countMap.getOrDefault(book1, 0) + 1);

		System.out.println("\n-----------------------------------------------------\n");
		System.out.println("countMap size: " + countMap.size());
		for (Map.Entry<Book, Integer> entry : countMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	private void checkHashMapMutability() {
		Employee employee1 = new Employee(1, "David", 77000);
		Employee employee3 = new Employee(2, "Josh", 77000);
		Employee employee2 = new Employee(3, "Jane", 55000);

		Map<Employee, String> employeeMap = new HashMap<>();
		employeeMap.put(employee1, "Marketing");
		employeeMap.put(employee2, "HR");
		employeeMap.put(employee3, "Dev Ops");


		System.out.println("\n-----------------------------------------------------\n");
		System.out.println("employeeMap size: " + employeeMap.size());
		for (Map.Entry<Employee, String> entry : employeeMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		employee1.setEmpId(55);

		System.out.println("\n-----------------------------------------------------\n");
		System.out.println("employee1: " + employeeMap.get(employee1));
		System.out.println("AFTER ID CHANGE: employeeMap size: " + employeeMap.size());
		for (Map.Entry<Employee, String> entry : employeeMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	private void checkTransitivity() {
		Person person1 = new Person(1, "John");
		Person person2 = new Person(1, "John");
		Person person3 = new Person(1, "John");

		System.out.println("\n-----------------------------------------------------\n");
		System.out.println("checkTransitivity(): person1.equals(person2): " + person1.equals(person2));
		System.out.println("checkTransitivity(): person2.equals(person3): " + person2.equals(person3));
		System.out.println("checkTransitivity(): person1.equals(person3): " + person1.equals(person3));
	}

	private void checkEqualsWithoutHashCode() {
		Product product1 = new Product ("Sugar-001", 6.00);
		Product product2 = new Product ("Milk-001", 9.65);
		Product product3 = new Product ("Sugar-001", 9.65);

		HashSet<Product> productsHashSet = new HashSet<>();
		productsHashSet.add(product1);
		productsHashSet.add(product2);
		productsHashSet.add(product3);

		// comment/uncomment hashCode override
		System.out.println("\n-----------------------------------------------------\n");
		System.out.println("checkEqualsWithoutHashCode(): productsHashSet.size(): " + productsHashSet.size());
		System.out.println("checkEqualsWithoutHashCode(): productsHashSet: " + productsHashSet);
	}

	private void checkHashMapCompositeKey() {
		FlightKey flight1 = new FlightKey("Toronto", "NYC", LocalDate.of(2025, 10, 1));
		FlightKey flight2 = new FlightKey("Toronto", "Montreal", LocalDate.of(2025, 9, 21));
		HashMap<FlightKey, Double> flights = new HashMap<>();
		flights.put(flight1, 350.99);
		flights.put(flight2, 235.00);

		System.out.println("\n-----------------------------------------------------\n");
		System.out.println("checkHashMapCompositeKey(): flight1 price: " + flights.get(flight1));
		System.out.println("checkHashMapCompositeKey(): flight2 price: " + flights.get(flight2));
	}

	private void checkSymmetryViolation() {
		Point p1 = new Point(2, 3);
		Point p2 = new Point(2, 5);

		System.out.println("\n-----------------------------------------------------\n");
		System.out.println("checkHashMapCompositeKey(): p1.equals(p2): " + p1.equals(p2));
		System.out.println("checkHashMapCompositeKey(): p2.equals(p1): " + p2.equals(p1));

		// p1 should be equal to p2 because we are comparing only by x
		System.out.println("\n-----------------------------------------------------\n");
		System.out.println("checkHashMapCompositeKey(): p1.equals(p2): " + p1.equals(p2));
		System.out.println("checkHashMapCompositeKey(): p2.equals(p1): " + p2.equals(p1));

	}

	private void checkHashSetTreeSet() {
		Student student1 = new Student(1, "John");
		Student student2 = new Student(2, "David");
		Student student3 = new Student(2, "Paul"); // duplicate
		Student student4 = new Student(3, "Anna");

		HashSet<Student> studentHashSet = new HashSet<>();
		studentHashSet.add(student1);
		studentHashSet.add(student2);
		studentHashSet.add(student3);
		studentHashSet.add(student4);

		System.out.println("\n-----------------------------------------------------\n");
		System.out.println("checkHashSetTreeSet(): studentHashSet: ");
		for (Student stud: studentHashSet) {
			System.out.println(stud);
		}

		// here duplicate is added because of compareTo() is by name
		Set<Student> studentTreeSet = new TreeSet<>();
		studentTreeSet.add(student1);
		studentTreeSet.add(student2);
		studentTreeSet.add(student3);
		studentTreeSet.add(student4);
		System.out.println("checkHashSetTreeSet(): studentTreeSet: ");
		for (Student stud: studentTreeSet) {
			System.out.println(stud);
		}
	}
}
