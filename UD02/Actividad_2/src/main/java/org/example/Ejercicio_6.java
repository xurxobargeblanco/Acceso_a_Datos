package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ejercicio_6 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String Dnombre = pedirTexto(sc,"Nombre de departamento :");
        selectDepartamento(Dnombre.trim());

        sc.close();

    }


    private static String pedirTexto(Scanner sc, String mensaxe){
        System.out.println(mensaxe);
        return sc.nextLine();
    }

    public static void selectDepartamento(String Dnombre){

        String sql = "SELECT DEP_NO FROM departamentos WHERE DNOMBRE = ?";

        try(Connection c = ConexionBD.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setString(1,Dnombre);

        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
