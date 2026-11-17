package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


///
/// CREATE TABLE categorias (
///     id     INT AUTO_INCREMENT PRIMARY KEY,
///     nombre VARCHAR(50) NOT NULL
/// );
///

public class Ejercicio_6 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Nombre de la categoría:");
        String nombre = sc.nextLine().trim();

        insertarCategoria(nombre);
    }

    public static void insertarCategoria(String nombre) {
        String sql = "INSERT INTO categorias (nombre) VALUES (?)";

        try (Connection c = ConexionDB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, nombre);

            int filas = ps.executeUpdate();
            System.out.println("Filas insertadas: " + filas);

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    System.out.println("Identificador generado: " + idGenerado);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
