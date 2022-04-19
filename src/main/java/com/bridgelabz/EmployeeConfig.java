package com.bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Purpose: Class for JDBC Connection.
 *
 * @author Aniket Kumbhar
 * @version 1.0
 * @since 18-03-2022
 */
public class EmployeeConfig {
    public static final String URL_JD = "jdbc:mysql://localhost:3306/payroll_service";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "Aniket@1675";
    static Connection connection = null;

    /**
     * Method :- Method for JDBC Connection.
     *
     * @return returning connection Object.
     */

    public static Connection getConfig() {
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Drivers loaded!!");
        connection = DriverManager.getConnection(URL_JD,USER_NAME,PASSWORD);
        System.out.println("connection Established!!");
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
        return connection;
    }
}
