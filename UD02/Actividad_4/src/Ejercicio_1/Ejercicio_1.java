package Ejercicio_1;

import java.sql.*;




public class Ejercicio_1 {
    public static void main(String[] args) {
        String sqlCliente = "INSERT INTO Cliente (nombre, email) VALUES (?, ?)";
        String sqlPedido = "INSERT INTO Pedido (cliente_id, descripcion) VALUES (?, ?)";

        try (Connection c = ConexionDB.getConnection()) {
            c.setAutoCommit(false);

            try {
                // 1. Insertar cliente y recuperar su id generado
                int idCliente;
                try (PreparedStatement ps = c.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, "Juan");
                    ps.setString(2, "juan@gmail.com");
                    ps.executeUpdate();

                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        rs.next();
                        idCliente = rs.getInt(1);
                    }
                }

                // 2. Insertar los dos pedidos con ese id
                try (PreparedStatement ps = c.prepareStatement(sqlPedido)) {
                    ps.setInt(1, idCliente);
                    ps.setString(2, "Pedido 1");
                    ps.executeUpdate();

                    ps.setInt(1, idCliente);   // para la prueba de error: usar 9999
                    ps.setString(2, "Pedido 2");
                    ps.executeUpdate();
                }

                // 3. Confirmar todo junto
                c.commit();
                System.out.println("Transacción confirmada. Cliente id = " + idCliente);

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                c.rollback();
                System.out.println("Rollback realizado");
            }

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}
