package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio_5 {

    public static void main(String[] args) {
        incrementarSalarioPrimerEmpleado();
    }

    public static void incrementarSalarioPrimerEmpleado() {
        String sql = "SELECT EMP_NO, APELLIDO, SALARIO FROM empleados ORDER BY EMP_NO";

        try (Connection c = ConexionDB.getConnection();
             Statement st = c.createStatement(
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.first()) {
                double salarioAntes = rs.getDouble("SALARIO");
                double salarioNuevo = salarioAntes * 1.10;

                System.out.println("Empleado: " + rs.getInt("EMP_NO") + " - " + rs.getString("APELLIDO"));
                System.out.println("Salario antes:   " + salarioAntes);

                rs.updateDouble("SALARIO", salarioNuevo);
                rs.updateRow();

                System.out.println("Salario después: " + rs.getDouble("SALARIO"));
            } else {
                System.out.println("La tabla empleados está vacía");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}