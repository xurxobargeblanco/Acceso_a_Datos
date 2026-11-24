package Ejercicio_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio_2 {
    public static void main(String[] args) {
        String sql = "SELECT * FROM empleados";

        try (Connection c = ConexionDB_2.getConnection()) {

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
            ConexionDB_2.close();
        }
    }
}