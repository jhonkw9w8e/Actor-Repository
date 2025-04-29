package com.example;

import com.example.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3306/sakila?useSSL=false&serverTimezone=UTC";
        String dbUser = "root";
        String sql = "SELECT actor_id, first_name, last_name FROM actor";

        try (
            Connection connection = DatabaseConnection.getInstance(dbUrl, dbUser);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            System.out.println("Listado de actores:");
            while (rs.next()) {
                int id = rs.getInt("actor_id");
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                System.out.printf("ID: %d - Nombre completo: %s%n", id, fullName);
            }
            System.out.println("Operación completada");
        } catch (SQLException e) {
            System.out.println("No se pudo establecer la conexión");
            e.printStackTrace();
        }
    }
}
