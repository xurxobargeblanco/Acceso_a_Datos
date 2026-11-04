package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio_1 {
    public static void main(String[] args) {
        listarEmpleados();
    }

    public static void listarEmpleados(){
        String sql = "SELECT EMP_NO, APELLIDO, OFICIO, SALARIO,DEP_NO FROM empleados";

        try(Connection connection = ConexionBD.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql))
        {
            System.out.println("Lista de empleados : ");

            while (resultSet.next()){
                int empNo = resultSet.getInt("EMP_NO");
                String apellido = resultSet.getString("APELLIDO");
                String oficio = resultSet.getString("OFICIO");
                double salirio = resultSet.getDouble("SALARIO");
                int depNo = resultSet.getInt("DEP_NO");
                String departamento;

                if (resultSet.wasNull()) departamento = "Sin departamento";
                else departamento = String.valueOf(depNo);

                System.out.println(empNo + " - " + apellido + " - " + oficio + " - " + salirio + " - " + departamento);
            }
        }catch (SQLException e){
            System.out.println("Error en al conexión : " + e.getMessage());
        }
    }
}
