package org.example.Ejercicio_2;

import org.example.Ejercicio_1.ConexionDB;

import java.sql.*;
import java.util.Scanner;

public class Ejercicio_2 {
    public static void main(String[] args) {
        String sql = "SELECT * FROM empleados";
        try(Connection c = ConexionDB.getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData rsmd = rs.getMetaData();

            int columnas = rsmd.getColumnCount();

            System.out.println("Número de columnas: " + columnas);

            for (int i = 1; i <= columnas ; i++) {
                System.out.println(rsmd.getColumnName(i) +" - " + rsmd.getColumnLabel(i) + " - " + rsmd.getColumnTypeName(i));
            }

            while (rs.next()){
                for (int i = 1; i <= columnas ; i++) {
                    System.out.print(rs.getObject(i) + "\t");
                }
            }

        }catch (SQLException e ) {
            System.out.println("Error : " + e.getErrorCode());
        }
    }
}
