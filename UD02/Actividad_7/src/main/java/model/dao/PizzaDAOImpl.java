package model.dao;

import model.dto.PizzaResumenDTO;
import model.entity.Pizza;
import model.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PizzaDAOImpl implements PizzaDAO{

    private Pizza mapearPizza(ResultSet rs)throws SQLException{
        return new Pizza(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getBigDecimal("precio"),
                rs.getBoolean("disponible"));
    }

    private PizzaResumenDTO mapearPizzaResumen(ResultSet rs)throws SQLException{
        return new PizzaResumenDTO(
                rs.getString("nombre"),
                rs.getBigDecimal("precio")
        );
    }

    @Override
    public List<Pizza> listarTodas() throws SQLException {
        String sql = "SELECT id, nombre, descripcion, precio, disponible FROM pizzas";
        List<Pizza> pizzas = new ArrayList<>();

        try(Connection c = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery()){
            while (rs.next()){
                pizzas.add(mapearPizza(rs));
            }
        }
        return pizzas;
    }

    @Override
    public Optional<Pizza> buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, nombre, descripcion, precio, disponible FROM pizzas WHERE id = ?";

        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearPizza(rs));
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Pizza> buscarPorNombre(String nome) throws SQLException {
        String sql = "SELECT id, nombre, descripcion, precio, disponible FROM pizzas WHERE nombre LIKE ?";
        List<Pizza> pizzas = new ArrayList<>();
        try (Connection c = ConnectionPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + nome + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    pizzas.add(mapearPizza(rs));
                }
            }
        }
        return pizzas;
    }

    @Override
    public boolean insertar(Pizza p) throws SQLException {
        String sql = "INSERT INTO pizzas (nombre, descripcion, precio, disponible) VALUES (?, ?, ?, ?)";

        try(Connection c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setString(1, p.getNombre());
            ps.setString(2,p.getDescripcion());
            ps.setBigDecimal(3,p.getPrecio());
            ps.setBoolean(4, p.isDisponible());

            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

    @Override
    public boolean actualizar(Pizza p) throws SQLException {
        String sql = "UPDATE pizzas SET nombre = ?, descripcion = ?, precio = ?, disponible = ? WHERE id = ?";

        try(Connection c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setString(1, p.getNombre());
            ps.setString(2,p.getDescripcion());
            ps.setBigDecimal(3,p.getPrecio());
            ps.setBoolean(4, p.isDisponible());
            ps.setInt(5, p.getId());

            int filas = ps.executeUpdate();
            return filas > 0;
        }

    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM pizzas WHERE id = ?";

        try(Connection c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1,id);

            int filas = ps.executeUpdate();
            return filas > 0;
        }

    }

    @Override
    public List<PizzaResumenDTO> listarResumido() throws SQLException {
        String sql = "SELECT nombre, precio FROM pizzas";
        List<PizzaResumenDTO> pizzas = new ArrayList<>();

        try(Connection c = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery()){
            while (rs.next()){
                pizzas.add(mapearPizzaResumen(rs));
            }
        }
        return pizzas;
    }
}
