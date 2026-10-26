package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio_1 {

    private static final String URL = "jdbc:h2:mem:testdb";
    private static final String USUARIO = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args) {

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             Statement sentencia = conexion.createStatement()) {

            System.out.println("Conectado á base de datos testdb");

            sentencia.executeUpdate("""
                    CREATE TABLE alumnos (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL,
                        edad INT
                    )
                    """);
            System.out.println("Tabla creada correctamente");

            sentencia.executeUpdate("INSERT INTO alumnos (nombre, edad) VALUES ('Ana García', 20)");
            System.out.println("Registro insertado");

            sentencia.executeUpdate("INSERT INTO alumnos (nombre, edad) VALUES ('Luis Pérez', 22)");
            System.out.println("Registro insertado");

            sentencia.executeUpdate("INSERT INTO alumnos (nombre, edad) VALUES ('Marta Souto', 19)");
            System.out.println("Registro insertado");

            int borrados = sentencia.executeUpdate("DELETE FROM alumnos");
            System.out.println("Registros borrados: " + borrados);

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}
