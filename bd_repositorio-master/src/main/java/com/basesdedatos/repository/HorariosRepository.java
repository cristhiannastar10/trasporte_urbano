package com.basesdedatos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.basesdedatos.config.DatabaseConnection;
import com.basesdedatos.model.Horarios;

public class HorariosRepository implements Repository<Horarios> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Horarios> findAll() throws SQLException {
        List<Horarios> horarios = new ArrayList<>();
        String sql = "SELECT * FROM horarios ORDER BY fecha";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Horarios horario = new Horarios(
                    resultSet.getInt("id_horario"),
                    resultSet.getDate("fecha"),
                    resultSet.getTime("hora_salida"),
                    resultSet.getTime("hora_llegada")
                );
                horarios.add(horario);
            }
        }
        return horarios;
    }

    @Override
    public Horarios findById(int id) throws SQLException {
        Horarios horario = null;
        String sql = "SELECT * FROM horarios WHERE id_horario = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    horario = new Horarios(
                        resultSet.getInt("id_horario"),
                        resultSet.getDate("fecha"),
                        resultSet.getTime("hora_salida"),
                        resultSet.getTime("hora_llegada")
                    );
                }
            }
        }
        return horario;
    }

    @Override
    public void save(Horarios horario) throws SQLException {
        String sql;
        if (horario.getId_horario() != null && horario.getId_horario() > 0) {
            sql = "UPDATE horarios SET fecha = ?, hora_salida = ?, hora_llegada = ? WHERE id_horario = ?";
        } else {
            sql = "INSERT INTO horarios (fecha, hora_salida, hora_llegada) VALUES (?, ?, ?)";
        }
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, horario.getFecha());
            statement.setTime(2, horario.getHora_salida());
            statement.setTime(3, horario.getHora_llegada());
            if (horario.getId_horario() != null && horario.getId_horario() > 0) {
                statement.setInt(4, horario.getId_horario());
            }
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Horarios horario) throws SQLException {
        if (horario.getId_horario() == null || horario.getId_horario() <= 0) {
            throw new SQLException("ID de horario inválido.");
        }
        String sql = "UPDATE horarios SET fecha = ?, hora_salida = ?, hora_llegada = ? WHERE id_horario = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, horario.getFecha());
            statement.setTime(2, horario.getHora_salida());
            statement.setTime(3, horario.getHora_llegada());
            statement.setInt(4, horario.getId_horario());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM horarios WHERE id_horario = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<Object[]> getHorariosOrdenadosPorFecha() throws SQLException {
        List<Object[]> horarios = new ArrayList<>();
        String sql = "SELECT id_horario, fecha, hora_salida, hora_llegada FROM horarios ORDER BY fecha";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                horarios.add(new Object[]{
                    resultSet.getInt("id_horario"),
                    resultSet.getDate("fecha"),
                    resultSet.getTime("hora_salida"),
                    resultSet.getTime("hora_llegada")
                });
            }
        }
        return horarios;
    }
}