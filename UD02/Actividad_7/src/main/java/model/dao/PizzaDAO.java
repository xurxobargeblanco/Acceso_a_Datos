package model.dao;

import model.dto.PizzaResumenDTO;
import model.entity.Pizza;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PizzaDAO {

    List<Pizza> listarTodas() throws SQLException;

    Optional<Pizza> buscarPorId(int id) throws SQLException;

    List<Pizza> buscarPorNome(String nome) throws SQLException;

    boolean insertar(Pizza p) throws SQLException;

    boolean actualizar(Pizza p) throws SQLException;

    boolean eliminar(int id) throws SQLException;

    List<PizzaResumenDTO> listardoResumido() throws SQLException;
}
