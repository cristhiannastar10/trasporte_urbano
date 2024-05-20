package com.basesdedatos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.basesdedatos.config.DatabaseConnection;
import com.basesdedatos.model.Rutas;

public class RutasRepository {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    public List<Rutas> findAllRutas() throws SQLException {
        List<Rutas> rutas = new ArrayList<>();
        String sql = "SELECT * FROM rutas";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Rutas ruta = new Rutas(
                    resultSet.getInt("id_rutas"),
                    resultSet.getString("punto_partida"),
                    resultSet.getString("destino"),
                    resultSet.getInt("horario"),
                    resultSet.getString("trayectoria"),
                    resultSet.getInt("cantidad_pasajeros")
                );
                rutas.add(ruta);
            }
        }
        return rutas;
    }

    public List<Object[]> findByHorario(int horario) throws SQLException {
        List<Object[]> rutas = new ArrayList<>();
        String sql = "SELECT id_rutas, punto_partida, destino, horario, trayectoria, cantidad_pasajeros FROM rutas WHERE horario = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, horario);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    rutas.add(new Object[]{
                        resultSet.getInt("id_rutas"),
                        resultSet.getString("punto_partida"),
                        resultSet.getString("destino"),
                        resultSet.getInt("horario"),
                        resultSet.getString("trayectoria"),
                        resultSet.getInt("cantidad_pasajeros")
                    });
                }
            }
        }
        return rutas;
    }

    public int countPasajerosEnRuta(String puntoPartida, String destino) throws SQLException {
        String sql = "SELECT COUNT(*) AS cantidad_pasajeros FROM rutas WHERE punto_partida = ? AND destino = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, puntoPartida);
            statement.setString(2, destino);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("cantidad_pasajeros");
                }
            }
        }
        return 0;
    }

    public List<Object[]> findByPuntoDePartida(String puntoPartida) throws SQLException {
        List<Object[]> rutas = new ArrayList<>();
        String sql = "SELECT id_rutas, punto_partida, destino, horario, trayectoria, cantidad_pasajeros FROM rutas WHERE punto_partida = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, puntoPartida);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    rutas.add(new Object[]{
                        resultSet.getInt("id_rutas"),
                        resultSet.getString("punto_partida"),
                        resultSet.getString("destino"),
                        resultSet.getInt("horario"),
                        resultSet.getString("trayectoria"),
                        resultSet.getInt("cantidad_pasajeros")
                    });
                }
            }
        }
        return rutas;
    }

    public void insert(Rutas ruta) throws SQLException {
        String sql = "INSERT INTO rutas (id_rutas, punto_partida, destino, horario, trayectoria, cantidad_pasajeros) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ruta.getId_rutas());
            statement.setString(2, ruta.getPunto_partida());
            statement.setString(3, ruta.getDestino());
            statement.setInt(4, ruta.getHorario());
            statement.setString(5, ruta.getTrayectoria());
            statement.setInt(6, ruta.getCantidad_pasajeros());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM rutas WHERE id_rutas = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}