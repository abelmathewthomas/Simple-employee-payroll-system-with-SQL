# Simple-employee-payroll-system-with-SQL
---

# Employee Payroll System

This is a simple Employee Payroll System implemented in Java Swing with MySQL database connectivity.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Usage](#usage)
- [Database Setup](#database-setup)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Employee Payroll System is a Java application that allows users to manage employee information, add new employees, view employee details, and track their work history. The application utilizes Java Swing for the graphical user interface and connects to a MySQL database for data storage.

## Features

- **Add Employee:** Easily add new employees to the system with relevant details such as ID, name, hours worked, overtime, date of birth, and gender.

- **View Employee Details:** View the work history of employees by entering their ID. The system retrieves and displays information such as name, hours worked, overtime, date of birth, and gender.

- **Database Connectivity:** The application uses JDBC to connect to a MySQL database, allowing for the persistent storage of employee information.

## Usage

To run the Employee Payroll System, follow these steps:

1. Ensure you have Java and MySQL installed on your system.
2. Compile and run the `EmployeePayrollSystemGUI.java` file.

## Database Setup

1. Install MySQL on your machine if not already installed.
2. Open a MySQL client and run the following SQL script to create the required database and table:

```sql
CREATE DATABASE IF NOT EXISTS employee;

USE employee;

CREATE TABLE IF NOT EXISTS employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    emp_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    hours_worked INT NOT NULL,
    overtime INT NOT NULL,
    dob DATE,
    gender VARCHAR(10),
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

3. Update the `initializeDatabase` method in the code with your MySQL username and password.

```java
String username = "your_username";
String password = "your_password";
```

## Contributing

If you find any issues or have suggestions for improvement, feel free to open an issue or create a pull request. Contributions are welcome!

## License

This project is licensed under the [MIT License](LICENSE).

---
