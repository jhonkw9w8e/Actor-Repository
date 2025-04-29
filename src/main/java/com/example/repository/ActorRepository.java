package com.example.repository;

import com.example.model.Actor;
import com.example.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorRepository implements Repository<Actor> {

    @Override
    public List<Actor> findAll() {
        List<Actor> actorsList = new ArrayList<>();
        String query = "SELECT actor_id, first_name, last_name FROM actor";
        try (Connection conn = DatabaseConnection.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int actorId = rs.getInt("actor_id");
                String name = rs.getString("first_name") + " " + rs.getString("last_name");
                actorsList.add(new Actor(actorId, name));
            }
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
        return actorsList;
    }

    @Override
    public Actor getByID(Integer actorId) {
        String query = "SELECT actor_id, first_name, last_name FROM actor WHERE actor_id = ?";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, actorId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("first_name") + " " + rs.getString("last_name");
                    return new Actor(actorId, name);
                }
            }
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
        return null;
    }

    @Override
    public void save(Actor actor) {
        String[] tokens = actor.getNombre().split(" ", 2);
        String query = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tokens[0]);
            stmt.setString(2, tokens.length > 1 ? tokens[1] : "");
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    actor.setId(keys.getInt(1));
                }
            }
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }

    @Override
    public void delete(Integer actorId) {
        String query = "DELETE FROM actor WHERE actor_id = ?";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, actorId);
            stmt.executeUpdate();
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }

    public Actor addNew(Actor actor) {
        String[] tokens = actor.getNombre().split(" ", 2);
        String query = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tokens[0]);
            stmt.setString(2, tokens.length > 1 ? tokens[1] : "");
            stmt.execute();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    actor.setId(keys.getInt(1));
                }
            }
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
        return actor;
    }
}
