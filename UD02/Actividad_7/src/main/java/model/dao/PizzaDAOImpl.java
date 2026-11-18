package model.dao;

import model.dto.PizzaResumenDTO;
import model.entity.Pizza;
import model.util.ConnectioPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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

    @Override
    public List<Pizza> listarTodas() throws SQLException {
        String sql = "SELECT id, nombre, descripcion, precio, disponible FROM pizzas";
        List<Pizza> pizzas = new ArrayList<>();

        try(Connection c = ConnectioPool.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet rs = preparedStatement.getResultSet()){
            while (rs.next()){
                pizzas.add(mapearPizza(rs));
            }
        }
        return pizzas;
    }

    @Override
    public Optional<Pizza> buscarPorId(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Pizza> buscarPorNombre(String nome) throws SQLException {
        return List.of();
    }

    @Override
    public boolean insertar(Pizza p) throws SQLException {
        return false;
    }

    @Override
    public boolean actualizar(Pizza p) throws SQLException {
        return false;
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        return false;
    }

    @Override
    public List<PizzaResumenDTO> listarResumido() throws SQLException {
        return List.of();
    }
}
