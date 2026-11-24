package Ejercicio_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio_1 {
    public static void main(String[] args) {
        String sql = "SELECT * FROM empleados";

        try (Connection c = ConexionDB_1.getConnection()) {

            try (PreparedStatement ps = c.prepareStatement(sql)) {

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        System.out.println(
                                rs.getString("nombre") + "\t" +
                                rs.getInt("idade") + "\t" +
                                rs.getString("departamento")
                        );
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());

        } finally {

            try {
                ConexionDB_1.close();
            } catch (SQLException e) {
                System.out.println("Erro no peche: " + e.getMessage());
            }
        }
    }
}
