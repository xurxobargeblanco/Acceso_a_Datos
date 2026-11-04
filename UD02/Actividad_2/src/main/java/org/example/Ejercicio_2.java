package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio_2 {
    public static void main(String[] args) {
        insertar();
    }

    public static void insertar(){

        int depNo = 55;
        String dnombre = "INFORMATICA";
        String dlocalidad = "OURENSE";

        String sql = "INSERT into departamentos VALUES (" + depNo + ", '" + dnombre + "', '" + dlocalidad + "')";

        try(Connection connection = ConexionBD.getConnection();
            Statement statement = connection.createStatement();
        ){
            int filas = statement.executeUpdate(sql);

            System.out.println("Registros insertados " + filas);
        }catch (SQLException e){
            System.out.println("Error en la insercción " + e.getMessage());
            String sqlstate = e.getSQLState();

            if (sqlstate != null && sqlstate.startsWith("23")){
                System.out.println("xa existe un departamento co código : " +depNo);
            }
        }
    }
}
