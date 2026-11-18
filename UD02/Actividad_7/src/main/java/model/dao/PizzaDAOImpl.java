package model.dao;

import model.dto.PizzaResumenDTO;
import model.entity.Pizza;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PizzaDAOImpl implements PizzaDAO{

    @Override
    public List<Pizza> listarTodas() throws SQLException {
        return List.of();
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
