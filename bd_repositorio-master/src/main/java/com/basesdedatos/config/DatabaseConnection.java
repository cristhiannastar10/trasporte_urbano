package com.basesdedatos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {
        // Evita la creación de instancias
    }

    public static Connection getInstance() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Asegúrate de que el controlador JDBC esté en el classpath
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/trasporte_urbano", // URL de conexión
                    "root", // Nombre de usuario
                    "Villaflor123?" // Contraseña
                );
            } catch (ClassNotFoundException e) {
                throw new SQLException("Error al cargar el controlador JDBC", e);
            }
        }
        return connection;
    }
}
