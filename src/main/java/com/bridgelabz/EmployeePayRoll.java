package com.bridgelabz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Purpose: Class Employee Payroll.
 *
 * @author Aniket Kumbhar
 * @version 1.0
 * @since 18-03-2022
 *
 */

public class EmployeePayRoll {
    public static void main(String[] args) {
        String FETCH = "SELECT * FROM employee_payroll";
        ArrayList<Employee> empList = new ArrayList<Employee>();
        EmployeeConfig eConfig = new EmployeeConfig();
        eConfig.getConfig();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = eConfig.getConfig().prepareStatement(FETCH);
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
            for (Employee i : empList) {
                System.out.println(i.toString());
            }
        }
        catch (SQLException e) {
            throw new EmployeeException("invalid column label");
        }
    }
}
