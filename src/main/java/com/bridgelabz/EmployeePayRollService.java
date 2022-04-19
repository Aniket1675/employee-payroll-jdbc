package com.bridgelabz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Purpose: Class EmployeePayRollService
 *
 * @author Aniket Kumbhar
 * @version 1.0
 * @since 18-03-2022
 *
 */

public class EmployeePayRollService {

    public ArrayList<Employee> empList;
    PreparedStatement preparedStatement;
    Connection connection = EmployeeConfig.getConfig();

    /**
     * created a generic type method which is expecting quaries type parameter
     * @param query
     * @return
     */

    public List<Employee> queryExecute(String query) {
        empList = new ArrayList<Employee>();
        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();

                employee.setEmpId(resultSet.getInt("id"));
                employee.setEmpName(resultSet.getString("name"));
                employee.setPhoneNumber(resultSet.getString("Salary"));
                employee.setEmpStart(resultSet.getString("Start_Date"));
                employee.setGender(resultSet.getString("Gender"));
                employee.setPhoneNumber(resultSet.getString("Phone"));
                employee.setAddress(resultSet.getString("Address"));
                employee.setBasicPay(resultSet.getDouble("Basic_Pay"));
                employee.setDeductions(resultSet.getDouble("Deductions"));
                employee.setTaxablePay(resultSet.getDouble("Taxable_Pay"));
                employee.setIncomeTax(resultSet.getDouble("Income_Tax"));
                employee.setNetPay(resultSet.getDouble("Net_Pay"));

                empList.add(employee);
            }
        } catch (SQLException e) {
            throw new EmployeeException("invalid column label");
        }
        return empList;
    }
    /**
     * this method is used to print records from the payroll_service table
     */
    public void display() {
        for (Employee i : empList) {
            System.out.println(i.toString());
        }
    }
    /**
     * this method is accepting two parameter and used for update the basic pay into the salary column from the payroll_service table
     * @param name
     * @param basicPay
     * @return
     */
    public double updateBasicPay(String name, double basicPay) {
        String UPDATE = "UPDATE employee_payroll SET BasicPay = ? WHERE Name = ?";
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setDouble(1, basicPay);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            System.out.println("update successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM employee_payroll";
        queryExecute(sql);
        for (Employee employee : empList) {
            if (employee.getEmpName().equals(name)) {
                return employee.getBasicPay();
            }
        }
        return 0.0;
    }

    /**
     * this is a parameterized method which is used ti print the employee from a range of given date
     * @param start
     * @param end
     */
    public void getEmployee(LocalDate start, LocalDate end) {
        ArrayList<Employee> empSelected = new ArrayList<>();
        String select = "SELECT * FROM employee_payroll WHERE EmpStart BETWEEN ? AND ?";
        String sDate = String.valueOf(start);
        String eDate = String.valueOf(end);
        try {
            preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, sDate);
            preparedStatement.setString(2, eDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();

                employee.setEmpId(resultSet.getInt("id"));
                employee.setEmpName(resultSet.getString("name"));
                employee.setPhoneNumber(resultSet.getString("Salary"));
                employee.setEmpStart(resultSet.getString("Start_Date"));
                employee.setGender(resultSet.getString("Gender"));
                employee.setPhoneNumber(resultSet.getString("Phone"));
                employee.setAddress(resultSet.getString("Address"));
                employee.setBasicPay(resultSet.getDouble("Basic_Pay"));
                employee.setDeductions(resultSet.getDouble("Deductions"));
                employee.setTaxablePay(resultSet.getDouble("Taxable_Pay"));
                employee.setIncomeTax(resultSet.getDouble("Income_Tax"));
                employee.setNetPay(resultSet.getDouble("Net_Pay"));

                empList.add(employee);
            }
            for (Employee employee : empSelected) {
                System.out.println(employee);
            }

        } catch (SQLException e) {
            throw new EmployeeException("Invalid date");
        }
    }
    /**
     *This method is used to find sum, average, min, max from the payroll_service table
     *
     */

    public void calculate() {
        Scanner scanner = new Scanner(System.in);

        final int EXIT = 6;
        int choice = 0;
        while (choice != EXIT) {
            System.out.println("enter your choice\n1. SUM\n2. AVG\n3. MIN\n4. MAX  \n5.COUNT\n6.EXIT\n");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    calculateQuery("SELECT Gender, SUM(BasicPay) FROM employee_payroll GROUP BY Gender");
                    break;

                case 2:
                    calculateQuery("SELECT Gender, AVG(BasicPay) FROM employee_payroll GROUP BY Gender");
                    break;

                case 3:
                    calculateQuery("SELECT Gender, MIN(BasicPay) FROM employee_payroll GROUP BY Gender");
                    break;
                case 4:
                    calculateQuery("SELECT Gender, MAX(BasicPay) FROM employee_payroll GROUP BY Gender");
                    break;
                case 5:
                    calculateQuery("SELECT Gender, COUNT(BasicPay) FROM employee_payroll GROUP BY Gender");
                    break;
            }
        }
    }
    /**
     * this method is used to print the basic pay by using the gender
     * @param calculate
     */

    public void calculateQuery(String calculate) {
        List<Employee> result = new ArrayList<Employee>();

        try {
            preparedStatement = connection.prepareStatement(calculate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setGender(resultSet.getString(1));
                employee.setBasicPay(resultSet.getDouble(2));

                result.add(employee);
            }
            if (calculate.contains("COUNT")) {
                for (Employee i : result) {
                    System.out.println("Gender: " + i.getGender() + " COUNT: " + i.getBasicPay());
                }
            } else {
                for (Employee i : result) {
                    System.out.println("Gender: " + i.getGender() + " Basic pay: " + i.getBasicPay());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}