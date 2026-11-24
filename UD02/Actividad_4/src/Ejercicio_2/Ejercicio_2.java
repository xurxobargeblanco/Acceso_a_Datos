package Ejercicio_2;

import Ejercicio_1.ConexionDB;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio_2 {

    public static void main(String[] args) {
        String sql = "INSERT INTO Cliente (nombre, email) VALUES (?, ?)";

        String[] nombres = {"Paco", "Luis", "Manolo", "Carmen", "Luisa"};
        // Para la prueba del error: repetir un email (por ejemplo, poner "paco@example.com" también en Luisa)
        String[] emails = {
                "paco@example.com",
                "luis@example.com",
                "manolo@example.com",
                "carmen@example.com",
                "luisa@example.com"
        };

        try (Connection c = ConexionDB.getConnection()) {
            // 1. Desactivar auto-commit
            c.setAutoCommit(false);

            try (PreparedStatement ps = c.prepareStatement(sql)) {

                // 2. Añadir las cinco inserciones al lote
                for (int i = 0; i < nombres.length; i++) {
                    ps.setString(1, nombres[i]);
                    ps.setString(2, emails[i]);
                    ps.addBatch();
                }

                // 3. Ejecutar el lote
                int[] resultados = ps.executeBatch();

                // 4. Mostrar el resultado
                for (int i = 0; i < resultados.length; i++) {
                    if (resultados[i] == Statement.SUCCESS_NO_INFO) {
                        System.out.println("Cliente " + (i + 1) + ": insertado (sin información de filas)");
                    } else {
                        System.out.println("Cliente " + (i + 1) + ": filas afectadas = " + resultados[i]);
                    }
                }

                // 5. Confirmar si todo ha ido bien
                c.commit();
                System.out.println("Lote confirmado correctamente");

            } catch (BatchUpdateException e) {
                System.out.println("Error en el lote: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                c.rollback();
                System.out.println("Rollback completo realizado");

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                c.rollback();
                System.out.println("Rollback completo realizado");
            }

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
        }
    }
}
