package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio_2 {

    private static final String URL = "jdbc:h2:./data/instituto";
    private static final String USUARIO = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args){
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             Statement sentencia = conexion.createStatement()) {

            System.out.println("Conectado á base de datos instituto");

            sentencia.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS ciclos (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL,
                        grado VARCHAR(50)
                    )
                    """);
            System.out.println("Tabla creada o ya existente");

            sentencia.executeUpdate("INSERT INTO ciclos (nombre, grado) "
                    + "VALUES ('Desarrollo de Aplicaciones Multiplataforma', 'superior')");
            System.out.println("Registro insertado");

            sentencia.executeUpdate("INSERT INTO ciclos (nombre, grado) "
                    + "VALUES ('Sistemas Microinformáticos y Redes', 'medio')");
            System.out.println("Registro insertado");

            int modificados = sentencia.executeUpdate("UPDATE ciclos "
                    + "SET nombre = 'Desarrollo de Aplicaciones Multiplataforma (DAM)' WHERE id = 1");
            System.out.println("Registros modificados: " + modificados);

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}
