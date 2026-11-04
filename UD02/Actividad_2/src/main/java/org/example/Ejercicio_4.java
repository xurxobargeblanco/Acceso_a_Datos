package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio_4 {
    public static void main(String[] args) {
        eliminar();
    }

    public static void eliminar(){

        String sql = "DELETE FROM empleados WHERE DEP_NO IS NULL";

        try(Connection connection = ConexionBD.getConnection();
            Statement statement = connection.createStatement()){

            int filas = statement.executeUpdate(sql);

            System.out.println("Registros eliminados : " + filas);

        }catch (SQLException e){
            System.out.println("Error no update : " + e.getMessage());
        }
    }
}
