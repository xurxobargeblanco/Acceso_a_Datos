package org.example;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/// DROP PROCEDURE IF EXISTS salarioMedio;
///
/// DELIMITER //
///
/// CREATE PROCEDURE salarioMedio(
///     IN numDepartamento INT,
///     OUT media DECIMAL(10,2)
/// )
/// BEGIN
///     SELECT AVG(salario)
///     INTO media
///     FROM empleados
///     WHERE departamento_id = numDepartamento;
/// END //
///
/// DELIMITER ;


public class Ejercicio_2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        try(Connection c = ConexionDB.getConnection()){
            int emp_no = pedirEntero(sc,"Introduce el numero del departamento:");
            System.out.println("Salario medio departamento " + emp_no + " : " + salarioMedio(c,emp_no));
        }catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
        }

    }

    public static double salarioMedio(Connection c , int departamento) throws SQLException {
        CallableStatement cs = c.prepareCall("{CALL salarioMedio(?, ?)}");

        cs.setInt(1,departamento);
        cs.registerOutParameter(2, Types.DECIMAL);

        cs.execute();

        double media = cs.getDouble(2);

        cs.close();

        return media;
    }

    private static int pedirEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.println(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor non válido, inténtao de novo");
            }
        }
    }

}
