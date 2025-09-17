CREATE DATABASE  IF NOT EXISTS `hb_task1`;
USE `hb_task1`;

-- Tables structure for table1

DROP TABLE IF EXISTS table1;
CREATE TABLE table1 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    department VARCHAR(50),
    salary DECIMAL(10,2),
    table1_col1 VARCHAR(50),
    table1_col2 VARCHAR(50),
    table1_col3 VARCHAR(50),
    table1_col4 VARCHAR(50),
    table1_col5 VARCHAR(50),
    table1_col6 VARCHAR(50),
    table1_col7 VARCHAR(50),
    table1_col8 VARCHAR(50),
    table1_col9 VARCHAR(50)
) ENGINE=InnoDB;

INSERT INTO table1 (name, department, salary, table1_col1, table1_col2) VALUES ('Alice', 'HR', 1900.00, 'Col1 value', 'Col2 value');
INSERT INTO table1 (name, department, salary, table1_col1, table1_col2) VALUES ('John', 'Marketing', 1900.00, 'Col1 value', 'Col2 value');
INSERT INTO table1 (name, department, salary, table1_col1, table1_col2) VALUES ('Finance', '', 1900.00, 'Col1 value', 'Col2 value');


-- Tables structure for table2

DROP TABLE IF EXISTS table2;
CREATE TABLE table2 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    details VARCHAR(50),
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
) ENGINE=InnoDB;

INSERT INTO table2 (details, active, table2_col1, table2_col2) VALUES ('Work from home', true, 'table 2 col 1 value', 'table 2 col 1 value');
INSERT INTO table2 (details, active, table2_col1, table2_col2) VALUES ('Work online', false, 'table 2 col 2 value', 'table 2 col 2 value');

-- Tables structure for table3

DROP TABLE IF EXISTS table3;
CREATE TABLE table3 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hobby VARCHAR(50),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    table3_col1 VARCHAR(50),
    table3_col2 VARCHAR(50),
    table3_col3 VARCHAR(50),
    table3_col4 VARCHAR(50),
    table3_col5 VARCHAR(50)
) ENGINE=InnoDB;

INSERT INTO table3 (hobby ) VALUES ('Sports');
INSERT INTO table3 (hobby ) VALUES ('Coding');
INSERT INTO table3 (hobby ) VALUES ('Games');


SELECT
  (SELECT name FROM table1 LIMIT 1) AS colFromTable1,
  (SELECT table2_col2 FROM table2 LIMIT 1) AS colFromTable2,
  (SELECT hobby FROM table3 LIMIT 1) AS colFromTable3;


