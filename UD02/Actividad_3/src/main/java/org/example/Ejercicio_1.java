package org.example;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/// DROP PROCEDURE IF EXISTS total_empleados;
///
/// DELIMITER //
///
/// CREATE PROCEDURE total_empleados()
///     SELECT COUNT(*) AS total FROM empleados;
///
/// DELIMITER ;

public class Ejercicio_1 {
    public static void main(String[] args){
        procedimientoSinParametros();
    }

    public static void procedimientoSinParametros() {

        String sql = "{CALL total_empleados()}";

        try (Connection conexion = ConexionDB.getConnection();
             CallableStatement cs = conexion.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            if (rs.next()) {
                int total = rs.getInt("total");
                System.out.println("Total de empleados: " + total);
            }

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
