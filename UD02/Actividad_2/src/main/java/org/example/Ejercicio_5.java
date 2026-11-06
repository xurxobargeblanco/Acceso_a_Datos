package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ejercicio_5 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int empNo = pedirEnteiro(sc, "Número de empleado:");
        String apellido = pedirTexto(sc, "Apellido:");
        String oficio = pedirTexto(sc, "Oficio:");
        double salario = pedirDouble(sc, "Salario:");
        int depNo = pedirEnteiro(sc, "Número de departamento:");

        insertarEmpleado(empNo, apellido, oficio, salario, depNo);

        sc.close();
    }

    private static String pedirTexto(Scanner sc, String mensaxe){
        System.out.println(mensaxe);
        return sc.nextLine();
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
    public static void insertarEmpleado(int empNO,String apellido, String oficio, double salario, int dep_no){
        String sql = "INSERT INTO empleados (EMP_NO, APELLIDO, OFOCIO, SALARIO, DEP_NO)" + "VALUES (?,?,?,?,?)";

        try(Connection connection = ConexionBD.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1,empNO);
            pstmt.setString(2,apellido);
            pstmt.setString(3,oficio);
            pstmt.setDouble(4,salario);
            pstmt.setInt(5,dep_no);

            int filas = pstmt.executeUpdate();
            System.out.println("Rexistros insertados : " + filas);

        }catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
        }
    }
}
