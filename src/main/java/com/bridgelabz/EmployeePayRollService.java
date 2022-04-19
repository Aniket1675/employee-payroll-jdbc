package com.bridgelabz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    Connection connection =EmployeeConfig.getConfig();

    public List<Employee> queryExecute(String query) {
        empList = new ArrayList<>();
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
        } catch (
                SQLException e) {
            throw new EmployeeException("invalid column label");
        }
        return empList;
    }

    public void display() {
        for (Employee i : empList) {
            System.out.println(i.toString());
        }
    }

    public double updateBasicPay(String empName, double basicPay) {
        String UPDATE = "UPDATE employee_payroll SET Basic_Pay = ? WHERE name = ?";
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setDouble(1, basicPay);
            preparedStatement.setString(2, empName);
            preparedStatement.executeUpdate();
            System.out.println("update successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM employee_payroll";
        queryExecute(sql);
        for (Employee employee : empList) {
            if (employee.getEmpName().equals(empName)) {
                return employee.getBasicPay();
            }
        }
        return 0.0;
    }
}