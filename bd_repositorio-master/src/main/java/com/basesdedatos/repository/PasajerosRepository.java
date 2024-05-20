package com.basesdedatos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.basesdedatos.config.DatabaseConnection;
import com.basesdedatos.model.Pasajeros;

public class PasajerosRepository implements Repository<Pasajeros> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Pasajeros> findAll() throws SQLException {
        List<Pasajeros> pasajeros = new ArrayList<>();
        String sql = "SELECT * FROM pasajeros";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Pasajeros pasajero = new Pasajeros(
                    resultSet.getInt("pasajeros_id"), // Asegúrate de que este nombre de columna sea correcto
                    resultSet.getString("nombre"), // Asegúrate de que este nombre de columna sea correcto
                    resultSet.getString("apellido"), // Asegúrate de que este nombre de columna sea correcto
                    resultSet.getString("numeroTelefono"), // Asegúrate de que este nombre de columna sea correcto
                    resultSet.getString("tipoSangre") // Asegúrate de que este nombre de columna sea correcto
                );
                pasajeros.add(pasajero);
            }
        }
        return pasajeros;
    }

    @Override
    public Pasajeros findById(int id) throws SQLException {
        Pasajeros pasajero = null;
        String sql = "SELECT * FROM pasajeros WHERE pasajeros_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    pasajero = new Pasajeros(
                        resultSet.getInt("pasajeros_id"), // Asegúrate de que este nombre de columna sea correcto
                        resultSet.getString("nombre"), // Asegúrate de que este nombre de columna sea correcto
                        resultSet.getString("apellido"), // Asegúrate de que este nombre de columna sea correcto
                        resultSet.getString("numeroTelefono"), // Asegúrate de que este nombre de columna sea correcto
                        resultSet.getString("tipoSangre") // Asegúrate de que este nombre de columna sea correcto
                    );
                }
            }
        }
        return pasajero;
    }

    @Override
    public void save(Pasajeros pasajero) throws SQLException {
        String sql;
        if (pasajero.getPasajeros_id() != null && pasajero.getPasajeros_id() > 0) {
            sql = "UPDATE pasajeros SET nombre = ?, apellido = ?, numeroTelefono = ?, tipoSangre = ? WHERE pasajeros_id = ?";
        } else {
            sql = "INSERT INTO pasajeros (nombre, apellido, numeroTelefono, tipoSangre) VALUES (?, ?, ?, ?)";
        }
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pasajero.getNombre());
            statement.setString(2, pasajero.getApellidos());
            statement.setString(3, pasajero.getNumeroTelefono());
            statement.setString(4, pasajero.getTipoSangre());
            if (pasajero.getPasajeros_id() != null && pasajero.getPasajeros_id() > 0) {
                statement.setInt(5, pasajero.getPasajeros_id());
            }
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Pasajeros pasajero) throws SQLException {
        if (pasajero.getPasajeros_id() == null || pasajero.getPasajeros_id() <= 0) {
            throw new SQLException("ID de pasajero inválido.");
        }
        String sql = "UPDATE pasajeros SET nombre = ?, apellido = ?, numeroTelefono = ?, tipoSangre = ? WHERE pasajeros_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pasajero.getNombre());
            statement.setString(2, pasajero.getApellidos());
            statement.setString(3, pasajero.getNumeroTelefono());
            statement.setString(4, pasajero.getTipoSangre());
            statement.setInt(5, pasajero.getPasajeros_id());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM pasajeros WHERE pasajeros_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<Pasajeros> findByTipoSangre(String tipoSangre) throws SQLException {
        List<Pasajeros> pasajeros = new ArrayList<>();
        String sql = "SELECT * FROM pasajeros WHERE tipoSangre = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tipoSangre);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Pasajeros pasajero = new Pasajeros(
                        resultSet.getInt("pasajeros_id"), // Asegúrate de que este nombre de columna sea correcto
                        resultSet.getString("nombre"), // Asegúrate de que este nombre de columna sea correcto
                        resultSet.getString("apellido"), // Asegúrate de que este nombre de columna sea correcto
                        resultSet.getString("numeroTelefono"), // Asegúrate de que este nombre de columna sea correcto
                        resultSet.getString("tipoSangre") // Asegúrate de que este nombre de columna sea correcto
                    );
                    pasajeros.add(pasajero);
                }
            }
        }
        return pasajeros;
    }

    public void updateTelefono(int id, String nuevoTelefono) throws SQLException {
        String sql = "UPDATE pasajeros SET numeroTelefono = ? WHERE pasajeros_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nuevoTelefono);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }
}