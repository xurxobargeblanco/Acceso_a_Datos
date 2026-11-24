
package org.example.Ejercicio_3;

import org.example.Ejercicio_1.ConexionDB;

import java.sql.*;

public class Ejercicio_3 {

    public static void main(String[] args) {

        String sql = "SELECT * FROM empleados WHERE idade > ? AND departamento = ?";

        try (Connection c = ConexionDB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            // 1. Obter os metadatos dos parámetros
            ParameterMetaData pmd = ps.getParameterMetaData();

            // 2. Mostrar o número de parámetros
            int parametros = pmd.getParameterCount();

            System.out.println("Número de parámetros: " + parametros);

            // 3. Mostrar o tipo SQL esperado de cada parámetro
            for (int i = 1; i <= parametros; i++) {

                System.out.println("Parámetro: " + i);

                try {

                    System.out.println( pmd.getParameterTypeName(i));

                } catch (SQLException e) {
                    System.out.println("Non se puido obter o tipo : " + e.getMessage());
                }
            }

            // 4. Asignar valores aos parámetros

            // Primeiro parámetro: idade
            ps.setInt(1, 20);

            // Segundo parámetro: departamento
            ps.setString(2,"pendientes");


            // 5. Executar a consulta
            try (ResultSet rs = ps.executeQuery()) {

                ResultSetMetaData rsmd = rs.getMetaData();
                int columnas = rsmd.getColumnCount();

                // 6. Percorrer e mostrar os resultados
                while (rs.next()) {

                    for (int i = 1; i <= columnas ; i++) {
                        System.out.print(rs.getObject(i) + "\t");

                    }
                    System.out.println();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


