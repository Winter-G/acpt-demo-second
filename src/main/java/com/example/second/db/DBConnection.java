package com.example.second.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection dbConnection; //reference variable

    private final Connection connection;


    private DBConnection() throws SQLException, ClassNotFoundException {
        //load Driver class to ram
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish the database connection
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehicle", "root", "gnwg2001");
    }

    public static DBConnection getDBConnection() throws SQLException, ClassNotFoundException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
