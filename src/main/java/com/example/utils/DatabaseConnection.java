package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection myConn;

    public static Connection getInstance(String url, String user) throws SQLException {
        if (myConn == null || myConn.isClosed()) {
            Properties props = new Properties();
            props.setProperty("user", user);
            myConn = DriverManager.getConnection(url, props);
        }
        return myConn;
    }
}
