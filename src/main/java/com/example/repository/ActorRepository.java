package com.example.repository;

import com.example.model.Actor;
import com.example.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorRepository implements Repository<Actor> {

    @Override
    public List<Actor> findAll() {
        List<Actor> list = new ArrayList<>();
        String sql = "SELECT actor_id, first_name, last_name FROM actor";
        try (Connection conn = DatabaseConnection.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("actor_id");
                String nombre = rs.getString("first_name") + " " + rs.getString("last_name");
                list.add(new Actor(id, nombre));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Actor getByID(Integer id) {
        String sql = "SELECT actor_id, first_name, last_name FROM actor WHERE actor_id = ?";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int actorId = rs.getInt("actor_id");
                    String nombre = rs.getString("first_name") + " " + rs.getString("last_name");
                    return new Actor(actorId, nombre);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void save(Actor actor) {
        String[] parts = actor.getNombre().split(" ", 2);
        String firstName = parts[0];
        String lastName = parts.length > 1 ? parts[1] : "";
        String sql = "INSERT INTO actor (actor_id, first_name, last_name) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, actor.getId());
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM actor WHERE actor_id = ?";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
