package org.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Explicitly register the driver
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/Biblioteca?useSSL=false",
                    "root",
                    ""
            );
            System.out.println("It worked!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public static Connection creatConnectionToMySQL() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Biblioteca?useSSL=false", "root", "");
    }
}