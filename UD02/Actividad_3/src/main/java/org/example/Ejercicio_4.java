package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio_4 {
    public static void main(String[] args){
        recorridoBidireccional();
    }

    public static void recorridoBidireccional(){
        String sql = "SELECT EMP_NO, APELLIDO, OFICIO, SALARIO FROM empleados ORDER BY EMP_NO";

        try(Connection c = ConexionDB.getConnection();
            Statement st = c.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs = st.executeQuery(sql)
        ){

            if(rs.first()) mostrar("Primero", rs);
            else {
                System.out.println("La tabla empleados está vacía");
                return;
            }

            if(rs.last()) mostrar("Último",rs);

            if(rs.previous()){
                mostrar("Anterior",rs);
            }else System.out.println("Anterior : No hay registros anteriores");

            if(rs.next()) mostrar("Siguiente",rs);
            else System.out.println("Siguiente : No hay siguiente");

            if (rs.absolute(3)) mostrar("Tercero", rs);
            else System.out.println("Tercero : No hay tercero");

        }catch (SQLException e){
            System.out.println("Errro : " + e.getMessage());
        }
    }

    private static void mostrar(String posicion, ResultSet rs) throws SQLException{
        System.out.println(posicion + " -> "
        + rs.getInt("EMP_NO") + " - "
        + rs.getString("APELLIDO") + " - "
        + rs.getString("OFICIO") + " - "
        + rs.getDouble("SALARIO"));
    }
}
