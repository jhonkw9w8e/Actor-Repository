package com.example;

import com.example.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url  = "jdbc:mysql://localhost:3306/actor";
        String user = "root";

        try (
            Connection conn = DatabaseConnection.getInstance(url, user);
            Statement  stmt = conn.createStatement();
            ResultSet  rs   = stmt.executeQuery("SELECT id, nombre FROM actores")
        ) {
            while (rs.next()) {
                Integer id     = rs.getInt("id");
                String  nombre = rs.getString("nombre");
                System.out.println("Actor ID: " + id + ", Nombre: " + nombre);
            }
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.out.println("Error Conexion");
            e.printStackTrace();
        }
    }
}