package model.service;

import model.dto.PizzaResumenDTO;
import model.entity.Pizza;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PizzaService {
    List<Pizza> listarTodas() throws SQLException;

    Optional<Pizza> buscarPorId(int id) throws SQLException;

    List<Pizza> buscarPorNombre(String nombre) throws SQLException;

    void insertar(Pizza p) throws SQLException;

    void actualizar(Pizza p) throws SQLException;

    void eliminar(int id) throws SQLException;

    List<PizzaResumenDTO> listarResumido() throws SQLException;
}
