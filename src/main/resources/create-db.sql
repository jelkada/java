DROP SCHEMA IF EXISTS hb_employees_app;
CREATE SCHEMA hb_employees_app;

use hb_employees_app;

-- SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE address (
  id INT AUTO_INCREMENT PRIMARY KEY,
  street VARCHAR(128) DEFAULT NULL,
  city VARCHAR(45) DEFAULT NULL,
  state VARCHAR(45) DEFAULT NULL,
  zip_code VARCHAR(12) DEFAULT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE department (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) DEFAULT NULL,
  location VARCHAR(45) DEFAULT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE project (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(45) DEFAULT NULL,
  budget DECIMAL(12, 2) DEFAULT NULL CHECK (budget >= 1000),
  department_id INT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  CONSTRAINT chk_project_budget CHECK (budget >= 10000),

  CONSTRAINT fk_project_department FOREIGN KEY (department_id) REFERENCES department(id)
	ON DELETE SET NULL
	ON UPDATE CASCADE
);

CREATE TABLE employee (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) DEFAULT NULL,
  email VARCHAR(100) UNIQUE DEFAULT NULL,
  salary DECIMAL(10, 2) DEFAULT NULL,
  age TINYINT DEFAULT NULL,
  address_id INT UNIQUE NULL,
  department_id INT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  CONSTRAINT fk_employee_department FOREIGN KEY (department_id) REFERENCES department(id)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);

CREATE TABLE employee_project (
  employee_id INT NOT NULL,
  project_id INT NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (employee_id, project_id),
  FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE,
  FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE

  -- CONSTRAINT max_3_projects_per_employee CHECK (
    -- (SELECT COUNT(*) FROM employee_project ep WHERE ep.employee_id = employee_id) <= 3
  -- )
);

CREATE TABLE task (
  id INT AUTO_INCREMENT PRIMARY KEY,
  description VARCHAR(255) NOT NULL,
  deadline DATE,
  employee_id INT NULL,
  project_id INT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  CONSTRAINT fk_task_employee FOREIGN KEY (employee_id) REFERENCES employee(id),
  CONSTRAINT fk_task_project FOREIGN KEY (project_id) REFERENCES project(id)
);

-- SET FOREIGN_KEY_CHECKS = 1;

-- Insert Departments
INSERT INTO department (name, location) VALUES
('Engineering', 'New York'),
('Marketing', 'San Francisco'),
('HR', 'Chicago');

-- Insert Addresses
INSERT INTO address (street, city, state, zip_code) VALUES
('123 Maple St', 'New York', 'NY', '10001'),
('456 Oak Ave', 'Brooklyn', 'NY', '11201'),
('789 Pine Rd', 'Queens', 'NY', '11375'),
('101 Elm St', 'San Francisco', 'CA', '94101'),
('202 Birch Blvd', 'San Francisco', 'CA', '94107'),
('303 Cedar Dr', 'Chicago', 'IL', '60601'),
('404 Spruce Ln', 'Chicago', 'IL', '60614'),
('505 Walnut Way', 'Chicago', 'IL', '60622'),
('606 Chestnut Ct', 'New York', 'NY', '10010'),
('707 Aspen Cir', 'Brooklyn', 'NY', '11215');

-- Insert Employees with address and department references
INSERT INTO employee (name, email, salary, age, address_id, department_id) VALUES
('Alice Smith', 'alice@example.com', 75000.00, 30, 1, 1),
('Bob Johnson', 'bob@example.com', 68000.00, 28, 2, 1),
('Charlie Davis', 'charlie@example.com', 82000.00, 35, 3, 1),
('Diana Moore', 'diana@example.com', 60000.00, 26, 4, 2),
('Ethan Brown', 'ethan@example.com', 61000.00, 29, 5, 2),
('Fiona Clark', 'fiona@example.com', 59000.00, 32, 6, 3),
('George Hall', 'george@example.com', 62000.00, 31, 7, 3),
('Hannah Lee', 'hannah@example.com', 63000.00, 27, 8, 3),
('Ian Miller', 'ian@example.com', 70000.00, 33, 9, 1),
('Julia Wilson', 'julia@example.com', 72000.00, 30, 10, 2);

-- Insert Projects assigned to departments
INSERT INTO project (title, budget, department_id) VALUES
('Project Apollo', 150000.00, 1),
('Project AI', 200000.00, 1),
('Project Hera', 180000.00, 1),
('Project Hermes', 120000.00, 2),
('Project Athena', 140000.00, 2),
('Project Poseidon', 160000.00, 2),
('Project Artemis', 130000.00, 3),
('Project Demeter', 110000.00, 3),
('Project Ares', 170000.00, 3),
('Project Hephaestus', 125000.00, 3);

INSERT INTO employee_project (employee_id, project_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 3),
(3, 2),
(4, 2),
(5, 4),
(6, 5),
(7, 7),
(8, 6),
(9, 9),
(10, 1),
(10, 8),
(10, 9),
(10, 10);

INSERT INTO task (description, deadline, employee_id, project_id)
VALUES
  ('Design homepage layout', '2025-10-01', 1, 1),
  ('Fix login bug', '2025-09-25', 1, 2),
  ('Write API documentation', '2025-10-05', 1, 3),
  ('Implement payment gateway', '2025-10-10', 2, 1),
  ('Conduct user testing', '2025-10-12', 2, 2),
  ('Optimize database queries', '2025-09-30', 3, 1),
  ('Create marketing materials', '2025-10-15', 3, 2),
  ('Refactor legacy code', '2025-10-03', 3, 3);
