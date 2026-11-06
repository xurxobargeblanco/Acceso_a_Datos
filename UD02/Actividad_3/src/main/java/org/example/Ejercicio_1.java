package org.example;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class Ejercicio_1 {
    public static void main(String[] args){

    }

    public static void procedimientoSinParametros(){
        String sql = "SELECT COUNT(*) FROM empleados";

        try(CallableStatement cs = ConexionDB.getConnection().prepareCall(sql)){

        }catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
        }
    }
}
