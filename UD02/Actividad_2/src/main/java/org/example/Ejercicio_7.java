package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ejercicio_7 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int Emp_No = pedirEnteiro(sc,"Introduce o ide de empleado: ");
        double comision = pedirDouble(sc,"Introduce una nova comisión");

        modificarEmpleado(Emp_No,comision);
    }

    public static void modificarEmpleado(int emp_No,double comision ){

        String sql = "UPDATE empleados SET COMISION = ? WHERE EMP_NO = ?";

        try(Connection c = ConexionBD.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)
        ){

            ps.setDouble(1,comision);
            ps.setInt(2,emp_No);

            int filas = ps.executeUpdate();

            if (filas < 0) System.out.println("Non hai empleados con ese id (" + emp_No + ")");
            else System.out.println("Modificaronse " + filas + " registros");

        }catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
        }
    }


    private static int pedirEnteiro (Scanner sc, String mensaxe){
        while (true) {
            System.out.println(mensaxe);
            try{
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor non válido, intenta outra vez");
            }
        }
    }

    private static double pedirDouble (Scanner sc, String mensaxe){
        while (true) {
            System.out.println(mensaxe);
            try{
                return Double.parseDouble(sc.nextLine().trim().replace(',','.'));
            } catch (NumberFormatException e) {
                System.out.println("Valor non válido, intenta outra vez");
            }
        }
    }
}
