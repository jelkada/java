CREATE DATABASE  IF NOT EXISTS `hb_task1`;
USE `hb_task1`;

-- Tables structure for table1

DROP TABLE IF EXISTS table1;
CREATE TABLE table1 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    department VARCHAR(50),
    table1_col1 VARCHAR(50),
    table1_col2 VARCHAR(50),
    table1_col3 VARCHAR(50),
    table1_col4 VARCHAR(50),
    table1_col5 VARCHAR(50),
    table1_col6 VARCHAR(50),
    table1_col7 VARCHAR(50),
    table1_col8 VARCHAR(50),
    table1_col9 VARCHAR(50)
) AUTO_INCREMENT = 100 ENGINE=InnoDB;

INSERT INTO table1 (name, department, table1_col1, table1_col2) VALUES ('Alice', 'HR', 'Col1 value', 'Col2 value');
INSERT INTO table1 (name, department, table1_col1, table1_col2) VALUES ('John', 'Marketing', 'Col1 value', 'Col2 value');
INSERT INTO table1 (name, department, table1_col1, table1_col2) VALUES ('David', 'Finance', 'Col1 value', 'Col2 value');
INSERT INTO table1 (name, department, table1_col1, table1_col2) VALUES ('Alice', 'Finance', '123', '');
INSERT INTO table1 (name, department) VALUES ('Jeremy', 'HR');
INSERT INTO table1 (name, department) VALUES ('John', 'Finance');
INSERT INTO table1 (name, department) VALUES ('John', 'Finance');

-- Tables structure for table2

DROP TABLE IF EXISTS table2;
CREATE TABLE table2 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    age INT,
    active BOOLEAN DEFAULT FALSE,
    table2_col1 VARCHAR(50),
    table2_col2 VARCHAR(50),
    table2_col3 VARCHAR(50),
    table2_col4 VARCHAR(50),
    table2_col5 VARCHAR(50),
    table2_col6 VARCHAR(50),
    table2_col7 VARCHAR(50),
    table2_col8 VARCHAR(50),
    table2_col9 VARCHAR(50)
) AUTO_INCREMENT = 100 ENGINE=InnoDB;

INSERT INTO table2 (age, active, table2_col1, table2_col2) VALUES (57, true, 'table 2 col 1 value', 'table 2 col 1 value');
INSERT INTO table2 (age, active, table2_col1, table2_col2) VALUES (32, false, 'table 2 col 2 value', 'table 2 col 2 value');
INSERT INTO table2 (age, active, table2_col1, table2_col2) VALUES (19, true, 'table 2 col 1 value', 'table 2 col 1 value');
INSERT INTO table2 (age, active, table2_col1, table2_col2) VALUES (23, false, 'table 2 col 2 value', 'table 2 col 2 value');
INSERT INTO table2 (age, active) VALUES (45, true);
INSERT INTO table2 (age, active) VALUES (61, false);
INSERT INTO table2 (age, active) VALUES (41, true);

-- Tables structure for table3

DROP TABLE IF EXISTS table3;
CREATE TABLE table3 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hobby VARCHAR(50),
    salary DECIMAL(10,2),
    table3_col1 VARCHAR(50),
    table3_col2 VARCHAR(50),
    table3_col3 VARCHAR(50),
    table3_col4 VARCHAR(50),
    table3_col5 VARCHAR(50)
) AUTO_INCREMENT = 100 ENGINE=InnoDB;

INSERT INTO table3 (hobby, salary) VALUES ('Surfing', 27000.33);
INSERT INTO table3 (hobby, salary) VALUES ('Sports', 69000.00);
INSERT INTO table3 (hobby, salary) VALUES ('Coding', 80000.60);
INSERT INTO table3 (hobby, salary) VALUES ('Games', 719000.30);
INSERT INTO table3 (hobby, salary) VALUES ('Ping Pong', 119000.00);
INSERT INTO table3 (hobby, salary) VALUES ('Games', 78000.20);
INSERT INTO table3 (hobby, salary) VALUES ('Games', 69000);

DROP VIEW IF EXISTS view_combined;
CREATE VIEW view_combined AS
SELECT
    table1.id,
    table1.name,
    table1.department,
    table2.age,
    table3.salary
FROM table1
JOIN table2 ON table1.id = table2.id
JOIN table3 ON table2.id = table3.id;