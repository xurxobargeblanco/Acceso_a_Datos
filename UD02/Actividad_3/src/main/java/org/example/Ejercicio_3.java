package org.example;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Ejercicio_3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el salario :");
        double salario = Double.parseDouble(sc.nextLine().trim());


    }

    public static void incrementarSalario(double salrio){
        String sql = "{CALL incrementar_salario(?)}";

        try(Connection c = ConexionDB.getConnection();
            CallableStatement cs = c.prepareCall(sql)){

            cs.setDouble(1,salrio);
            cs.registerOutParameter(1, Types.DOUBLE);

            cs.execute();

            double nuevoSalario = cs.getDouble(1);

            System.out.println("Nuevo salario : " + nuevoSalario);
        }catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
        }
    }
}
