-- Create database
CREATE DATABASE IF NOT EXISTS employee_db;
USE employee_db;

-- Create employees table
CREATE TABLE IF NOT EXISTS employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    department VARCHAR(50),
    salary DECIMAL(10, 2),
    hire_date DATE,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO employees (first_name, last_name, email, department, salary, hire_date, is_active) VALUES
('John', 'Doe', 'john.doe@example.com', 'Engineering', 85000.00, '2020-01-15', TRUE),
('Jane', 'Smith', 'jane.smith@example.com', 'Engineering', 92000.00, '2019-06-20', TRUE),
('Michael', 'Johnson', 'michael.j@example.com', 'Sales', 75000.00, '2021-03-10', TRUE),
('Emily', 'Brown', 'emily.brown@example.com', 'Marketing', 68000.00, '2020-09-01', TRUE),
('David', 'Wilson', 'david.wilson@example.com', 'Engineering', 110000.00, '2018-04-15', TRUE),
('Sarah', 'Davis', 'sarah.davis@example.com', 'HR', 65000.00, '2021-07-22', TRUE),
('Robert', 'Martinez', 'robert.m@example.com', 'Sales', 82000.00, '2019-11-05', TRUE),
('Lisa', 'Anderson', 'lisa.anderson@example.com', 'Marketing', 71000.00, '2020-02-14', TRUE),
('James', 'Taylor', 'james.taylor@example.com', 'Engineering', 95000.00, '2019-08-30', TRUE),
('Jennifer', 'Thomas', 'jennifer.t@example.com', 'HR', 62000.00, '2022-01-10', TRUE);

-- Create stored procedure to get employees by department
DELIMITER $$

CREATE PROCEDURE get_employees_by_department(IN dept_name VARCHAR(50))
BEGIN
    SELECT 
        id,
        first_name,
        last_name,
        email,
        department,
        salary,
        hire_date,
        is_active
    FROM employees
    WHERE department = dept_name AND is_active = TRUE
    ORDER BY salary DESC;
END$$

DELIMITER ;

-- Create stored procedure to get salary range by department
DELIMITER $$

CREATE PROCEDURE get_salary_range_by_department(IN dept_name VARCHAR(50))
BEGIN
    SELECT 
        department,
        COUNT(*) as employee_count,
        MIN(salary) as min_salary,
        MAX(salary) as max_salary,
        AVG(salary) as avg_salary
    FROM employees
    WHERE department = dept_name AND is_active = TRUE
    GROUP BY department;
END$$

DELIMITER ;

-- Create a user-defined function to calculate years of service
DELIMITER $$

CREATE FUNCTION calculate_years_of_service(hire_date DATE)
RETURNS INT
DETERMINISTIC
BEGIN
    RETURN TIMESTAMPDIFF(YEAR, hire_date, CURDATE());
END$$

DELIMITER ;

-- Create a user-defined function to categorize employee by salary
DELIMITER $$

CREATE FUNCTION get_salary_grade(salary DECIMAL(10,2))
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN
    DECLARE grade VARCHAR(20);
    
    IF salary < 50000 THEN
        SET grade = 'Entry Level';
    ELSEIF salary < 80000 THEN
        SET grade = 'Mid Level';
    ELSEIF salary < 120000 THEN
        SET grade = 'Senior Level';
    ELSE
        SET grade = 'Executive Level';
    END IF;
    
    RETURN grade;
END$$

DELIMITER ;

-- Example queries using stored procedures and UDFs
-- CALL get_employees_by_department('Engineering');
-- CALL get_salary_range_by_department('Sales');
-- SELECT first_name, last_name, hire_date, calculate_years_of_service(hire_date) as years_of_service FROM employees;
-- SELECT first_name, last_name, salary, get_salary_grade(salary) as grade FROM employees;