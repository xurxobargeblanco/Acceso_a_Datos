package Ejercicio_1;

import java.sql.*;


public class Ejercicio_1 {
    public static void main(String[] args) {

            c.setAutoCommit(false);
            String sql1 = "INSERT INTO Cliente (nombre, email) VALUES (?, ?)";
            String sql2 = "INSERT INTO Pedido (cliente_id, descripcion) VALUES (?, ?)";
            int idCliente;
            try (PreparedStatement ps = .prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, "Juan");
                ps.setString(2, "juan@gmail.com");
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    rs.next();
                    idCliente = rs.getInt(1);
                }

                try(PreparedStatement ps1 = c.prepareStatement(sql2)){
                    ps1.setInt(1,idCliente);
                    ps1.setString(2,"Pedido 1");
                    ps1.executeUpdate();
                    ps1.setString(2,"Pedido 2");
                    ps1.executeUpdate();
                }
                c.commit();
            }catch (SQLException e){
                System.out.println(e.getErrorCode());
                c.rollback();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
