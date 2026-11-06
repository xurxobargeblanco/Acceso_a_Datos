package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Ejercicio_6 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String dnombre = pedirTexto(sc,"Nombre de departamento :");
        mostarEmpleadoDepartamento(dnombre.trim());

    }

    private static String pedirTexto(Scanner sc, String mensaxe){
        System.out.println(mensaxe);
        return sc.nextLine();
    }

    public static void mostarEmpleadoDepartamento(String dnombre){

        try(Connection c = ConexionBD.getConnection()){

            int depNo = selectIdDepartamento(dnombre,c);

            if (depNo == -1) {
                System.out.println("No exixte ningun departamento llamamdo : " + dnombre);
                return;
            }

            mostarEmpleados(c,depNo);

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private static int selectIdDepartamento(String Dnombre,Connection c) throws  SQLException{

        String sql = "SELECT DEP_NO FROM departamentos WHERE DNOMBRE = ?";

        try(PreparedStatement ps = c.prepareStatement(sql)){

            ps.setString(1,Dnombre);

            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()) return rs.getInt("DEP_NO");
                return -1;
            }
        }
    }

    private static void mostarEmpleados(Connection c,int DepNo) throws  SQLException{
        String sql = "SELECT EMP_NO, APELLIDO, OFICIO, SALARIO FROM empleados WHERE DEP_NO = ?";

        try(PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,DepNo);

            try(ResultSet rs = ps.executeQuery()){
                boolean hayEmpleados = false;

                System.out.println("Empleados de " + DepNo + " :");
                while (rs.next()){
                    hayEmpleados = true;
                    int emp_no = rs.getInt("EMP_NO");
                    String apellidos = rs.getString("APELLIDO");
                    String oficio = rs.getString("OFICIO");
                    double salario = rs.getDouble("SALARIO");

                    System.out.println(emp_no + " - " + apellidos + " - " + oficio + " - " + salario);
                }

                if (!hayEmpleados) System.out.println("Departamento sin empleados");
            }
        }
    }
}
