package org.example.Ejercicio_1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Ejercicio_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try(Connection c = ConexionDB.getConnection()){
            DatabaseMetaData dbmd = c.getMetaData();

            System.out.println("El nombre de la SGBD es : " + dbmd.getDatabaseProductName() + "\n" +
                            "La version de la SGBD es : " + dbmd.getDatabaseProductVersion() + "\n" +
                            "El nombre del driver JDBC es : " + dbmd.getDriverName()+ "\n" +
                            "La versión del driver JDBC es : " + dbmd.getDriverVersion());

            if(dbmd.supportsBatchUpdates()) System.out.println("La BD soporta lotes");
            else System.out.println("La BD no soporta lotes");

            if(dbmd.supportsTransactions()) System.out.println("La BD soporta transacciones");
            else System.out.println("La BD no soporta transacciones");

            if(dbmd.supportsSavepoints()) System.out.println("La BD soporta save points");
            else System.out.println("La BD no soporta save points");

            if(dbmd.supportsGetGeneratedKeys()) System.out.println("La BD soporta claves generadas");
            else System.out.println("La BD no soporta claves generadas");

            try (ResultSet rs = dbmd.getTables(c.getCatalog(), null, "%", new String[]{"TABLE"})) {
                while (rs.next()) {
                    System.out.println(rs.getString("TABLE_NAME"));
                }
            }

            System.out.println("Que tabla quieres listar ?");
            String s = sc.nextLine().trim();


            try (ResultSet rs = dbmd.getColumns(c.getCatalog(), null, s, "%")) {

                while (rs.next()) {

                    String nombre = rs.getString("COLUMN_NAME");
                    String tipo = rs.getString("TYPE_NAME");
                    int tamano = rs.getInt("COLUMN_SIZE");
                    int nulos = rs.getInt("NULLABLE");

                    System.out.println("Nombre: " + nombre);
                    System.out.println("Tipo: " + tipo);
                    System.out.println("Tamaño: " + tamano);

                    if (nulos == DatabaseMetaData.columnNullable) {
                        System.out.println("Admite nulos: Si");
                    } else {
                        System.out.println("Admite nulos: No");
                    }

                    System.out.println("----------------------");
                }
            }

            try (ResultSet rs = dbmd.getPrimaryKeys(c.getCatalog(), null, s)) {

                while (rs.next()) {

                    String columna = rs.getString("COLUMN_NAME");

                    System.out.println("Clave primaria: " + columna);
                }
            }

        }catch (SQLException e){
            System.out.println("Error : " + e.getErrorCode());
        }
    }
}
