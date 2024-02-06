package employeepayroll;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeePayrollSystemGUI {
    private static Connection conn;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmployeePayrollSystemGUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Employee Payroll System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.lightGray);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.setBackground(Color.GREEN);
        addEmployeeButton.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(addEmployeeButton, constraints);

        JButton viewEmployeeButton = new JButton("View Employee Details");
        viewEmployeeButton.setBackground(Color.BLUE);
        viewEmployeeButton.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(viewEmployeeButton, constraints);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        addEmployeeButton.addActionListener(e -> addEmployee(frame));
        viewEmployeeButton.addActionListener(e -> showEmployeeHistory(frame));
        initializeDatabase();
    }

    private static void addEmployee(JFrame frame) {
    	JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.lightGray);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel idLabel = new JLabel("Employee ID:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        idLabel.setForeground(Color.BLUE);
        panel.add(idLabel, constraints);

        JTextField idField = new JTextField(20);
        constraints.gridx = 1;
        idField.setBackground(Color.WHITE);
        panel.add(idField, constraints);

        JLabel nameLabel = new JLabel("Name:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        nameLabel.setForeground(Color.BLUE);
        panel.add(nameLabel, constraints);

        JTextField nameField = new JTextField(20);
        constraints.gridx = 1;
        nameField.setBackground(Color.WHITE);
        panel.add(nameField, constraints);

        JLabel hoursWorkedLabel = new JLabel("Hours Worked:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        hoursWorkedLabel.setForeground(Color.BLUE);
        panel.add(hoursWorkedLabel, constraints);

        JTextField hoursWorkedField = new JTextField(20);
        constraints.gridx = 1;
        hoursWorkedField.setBackground(Color.WHITE);
        panel.add(hoursWorkedField, constraints);

        JLabel overtimeLabel = new JLabel("Overtime Worked:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        overtimeLabel.setForeground(Color.BLUE);
        panel.add(overtimeLabel, constraints);

        JTextField overtimeField = new JTextField(20);
        constraints.gridx = 1;
        overtimeField.setBackground(Color.WHITE);
        panel.add(overtimeField, constraints);

        JLabel dobLabel = new JLabel("Date of Birth (YYYY-MM-DD):");
        constraints.gridx = 0;
        constraints.gridy = 4;
        dobLabel.setForeground(Color.BLUE);
        panel.add(dobLabel, constraints);

        JTextField dobField = new JTextField(20);
        constraints.gridx = 1;
        dobField.setBackground(Color.WHITE);
        panel.add(dobField, constraints);

        JLabel genderLabel = new JLabel("Gender:");
        constraints.gridx = 0;
        constraints.gridy = 5;
        genderLabel.setForeground(Color.BLUE);
        panel.add(genderLabel, constraints);

        String[] genders = {"Male", "Female", "Other"};
        JComboBox<String> genderDropdown = new JComboBox<>(genders);
        constraints.gridx = 1;
        genderDropdown.setBackground(Color.WHITE);
        panel.add(genderDropdown, constraints);

        JButton addButton = new JButton("Add Employee");
        addButton.setBackground(Color.GREEN);
        addButton.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        panel.add(addButton, constraints);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        addButton.addActionListener(e -> addEmployee(idField, nameField, hoursWorkedField, overtimeField, dobField, genderDropdown, frame));
        initializeDatabase();

    }

    private static void showEmployeeHistory(JFrame frame) {
        JFrame historyFrame = new JFrame("Employee History");
        historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel historyPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel historyLabel = new JLabel("Employee History (Enter ID):");
        constraints.gridx = 0;
        constraints.gridy = 0;
        historyLabel.setForeground(Color.BLUE);
        historyPanel.add(historyLabel, constraints);

        JTextField historyIdField = new JTextField(20);
        constraints.gridx = 1;
        historyIdField.setBackground(Color.WHITE);
        historyPanel.add(historyIdField, constraints);

        JButton showHistoryButton = new JButton("Show History");
        showHistoryButton.setBackground(Color.BLUE);
        showHistoryButton.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        historyPanel.add(showHistoryButton, constraints);

        JTextArea historyTextArea = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        historyPanel.add(scrollPane, constraints);

        showHistoryButton.addActionListener(e -> showEmployeeHistory(historyIdField, historyTextArea));
        historyFrame.add(historyPanel);
        historyFrame.pack();
        historyFrame.setVisible(true);
    }

    private static void addHistoryComponents(JPanel panel, GridBagConstraints constraints) {
        JLabel historyLabel = new JLabel("Employee History (Enter ID):");
        constraints.gridx = 0;
        constraints.gridy = 7;
        historyLabel.setForeground(Color.BLUE);
        panel.add(historyLabel, constraints);

        JTextField historyIdField = new JTextField(20);
        constraints.gridx = 1;
        historyIdField.setBackground(Color.WHITE);
        panel.add(historyIdField, constraints);

        JButton showHistoryButton = new JButton("Show History");
        showHistoryButton.setBackground(Color.BLUE);
        showHistoryButton.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        panel.add(showHistoryButton, constraints);

        JTextArea historyTextArea = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 2;
        panel.add(scrollPane, constraints);

        showHistoryButton.addActionListener(e -> showEmployeeHistory(historyIdField, historyTextArea));
    }

    private static void showEmployeeHistory(JTextField historyIdField, JTextArea historyTextArea) {
        String idText = historyIdField.getText();

        if (idText.isEmpty()) {
            showErrorDialog(null, "Please enter the Employee ID.");
            return;
        }

        int empId = Integer.parseInt(idText);
        try {
            String sql = "SELECT * FROM employees WHERE emp_id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, empId);
                ResultSet resultSet = preparedStatement.executeQuery();

                historyTextArea.setText(""); // Clear the text area before displaying history

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int hoursWorked = resultSet.getInt("hours_worked");
                    int overtime = resultSet.getInt("overtime");
                    String dob = resultSet.getString("dob");
                    String gender = resultSet.getString("gender");

                    // Append history to the text area
                    historyTextArea.append("Employee ID: " + empId + "\nName: " + name +
                            "\nHours Worked: " + hoursWorked + "\nOvertime: " + overtime +
                            "\nDate of Birth: " + dob + "\nGender: " + gender + "\n\n");
                }
            } catch (SQLException ex) {
                handleDatabaseError(ex);
            }
        } catch (NumberFormatException ex) {
            showErrorDialog(null, "Invalid Employee ID. Please enter a valid number.");
        }
    }


    private static void initializeDatabase() {
        String url = "jdbc:mysql://localhost:3306/employee";
        String username = "root"; // Replace with your MySQL username
        String password = "Abel10062003"; // Replace with your MySQL password

        try {
            conn = DriverManager.getConnection(url, username, password);
            createEmployeeTable();
        } catch (SQLException e) {
            handleDatabaseError(e);
        }
    }

    private static void createEmployeeTable() {
        String sql = "CREATE TABLE IF NOT EXISTS employees (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "emp_id INT NOT NULL, " +
                "name VARCHAR(100) NOT NULL, " +
                "hours_worked INT NOT NULL, " +
                "overtime INT NOT NULL, " +
                "dob DATE, " +
                "gender VARCHAR(10), " +
                "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            handleDatabaseError(e);
        }
    }

    private static void addEmployee(JTextField idField, JTextField nameField, JTextField hoursWorkedField, JTextField overtimeField, JTextField dobField, JComboBox<String> genderDropdown, JFrame frame) {
        String idText = idField.getText();
        String name = nameField.getText();
        String hoursWorkedText = hoursWorkedField.getText();
        String overtimeText =

 overtimeField.getText();
        String dob = dobField.getText();
        String selectedGender = (String) genderDropdown.getSelectedItem();

        if (idText.isEmpty() || name.isEmpty() || hoursWorkedText.isEmpty() || overtimeText.isEmpty() || dob.isEmpty()) {
            showErrorDialog(frame, "All fields are required.");
            return;
        }

        try {
            int empId = Integer.parseInt(idText);
            int hoursWorked = Integer.parseInt(hoursWorkedText);
            int overtime = Integer.parseInt(overtimeText);
            insertEmployeeToDatabase(empId, name, hoursWorked, overtime, dob, selectedGender);
            clearFields(idField, nameField, hoursWorkedField, overtimeField, dobField);
            showSuccessDialog(frame, "Employee added successfully!");

            // Show the calculated salary details
            showSalaryDetails(hoursWorked, overtime);
        } catch (NumberFormatException ex) {
            showErrorDialog(frame, "Invalid input format. Please enter valid data.");
        }
    }

    private static void insertEmployeeToDatabase(int empId, String name, int hoursWorked, int overtime, String dob, String gender) {
        String sql = "INSERT INTO employees (emp_id, name, hours_worked, overtime, dob, gender) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, empId);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, hoursWorked);
            preparedStatement.setInt(4, overtime);
            preparedStatement.setString(5, dob);
            preparedStatement.setString(6, gender);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleDatabaseError(e);
        }
    }

    private static void handleDatabaseError(SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error accessing the database.", "Error", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    private static void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    private static void showErrorDialog(JFrame frame, String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private static void showSuccessDialog(JFrame frame, String message) {
        JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showSalaryDetails(int hoursWorked, int overtime) {
        int salary = ((hoursWorked * 150) + (overtime * 200))*30;
        String message = "Hours Worked: " + hoursWorked + "\nOvertime Worked: " + overtime + "\nCalculated Salary: $" + salary;
        JOptionPane.showMessageDialog(null, message, "Salary Details", JOptionPane.INFORMATION_MESSAGE);
    }
}