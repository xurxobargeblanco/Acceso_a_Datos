package Ejercicio_3;

import Ejercicio_1.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

public class Ejercicio_3 {

    public static void main(String[] args) {
        String sql = "INSERT INTO Cliente (nombre, email) VALUES (?, ?)";

        try (Connection c = ConexionDB.getConnection()) {
            c.setAutoCommit(false);

            try (PreparedStatement ps = c.prepareStatement(sql)) {

                // 1. Insertar Ana
                ps.setString(1, "Ana");
                ps.setString(2, "ana@ana.ana");
                ps.executeUpdate();
                System.out.println("Ana insertada");

                // 2. Savepoint DESPUÉS de insertar a Ana
                Savepoint sp = c.setSavepoint();

                // 3. Intentar insertar a Luis con el mismo email que Ana
                try {
                    ps.setString(1, "Luis");
                    ps.setString(2, "ana@ana.ana");
                    ps.executeUpdate();
                    System.out.println("Luis insertado");

                } catch (SQLException e) {
                    // 4. Mostrar mensaje y SQLState
                    System.out.println("Error: " + e.getMessage());
                    System.out.println("SQLState: " + e.getSQLState());

                    if (e.getSQLState() != null && e.getSQLState().startsWith("23")) {
                        // Violación de integridad: recuperable, volvemos al savepoint
                        c.rollback(sp);
                        System.out.println("Rollback parcial hasta el savepoint (Ana se conserva)");
                    } else {
                        // Error inesperado: no se puede continuar con seguridad
                        c.rollback();
                        System.out.println("Error inesperado: rollback completo");
                        return;
                    }
                }

                // 5. Insertar a Marta
                ps.setString(1, "Marta");
                ps.setString(2, "marta@ana.ana");
                ps.executeUpdate();
                System.out.println("Marta insertada");

                // 6. Confirmar
                c.commit();
                System.out.println("Transacción confirmada");

            } catch (SQLException e) {
                System.out.println("Error inesperado: " + e.getMessage());
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
