package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio_3 {
    public static void main(String[] args) {
        incrementar();
    }

    public static void incrementar(){

        String sql = "UPDATE empleados SET SALARIO = SALARIO * 1.05";

        try(Connection connection = ConexionBD.getConnection();
            Statement statement = connection.createStatement()){

            int filas = statement.executeUpdate(sql);

            System.out.println("Registros modificados : " + filas);

        }catch (SQLException e){
            System.out.println("Error no update : " + e.getMessage());
        }
    }
}
