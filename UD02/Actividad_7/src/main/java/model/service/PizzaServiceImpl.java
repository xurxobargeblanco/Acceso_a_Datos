package model.service;

import model.dao.PizzaDAO;
import model.dto.PizzaResumenDTO;
import model.entity.Pizza;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PizzaServiceImpl implements PizzaService {

    private final PizzaDAO dao;

    public PizzaServiceImpl(PizzaDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Pizza> listarTodas() throws SQLException {
        return dao.listarTodas();
    }

    @Override
    public Optional<Pizza> buscarPorId(int id) throws SQLException {
        return dao.buscarPorId(id);
    }

    @Override
    public List<Pizza> buscarPorNombre(String nombre) throws SQLException {
        return dao.buscarPorNombre(nombre);
    }

    @Override
    public void insertar(Pizza p) throws SQLException {
        validar(p);
        p.setNombre(p.getNombre().trim());
        dao.insertar(p);
    }

    @Override
    public void actualizar(Pizza p) throws SQLException {
        validar(p);
        p.setNombre(p.getNombre().trim());
        if (!dao.actualizar(p)) {
            throw new IllegalArgumentException("No existe ninguna pizza con id " + p.getId());
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        if (!dao.eliminar(id)) {
            throw new IllegalArgumentException("No existe ninguna pizza con id " + id);
        }
    }

    @Override
    public List<PizzaResumenDTO> listarResumido() throws SQLException {
        return dao.listarResumido();
    }

    private void validar(Pizza p) {
        if (p.getNombre() == null || p.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (p.getPrecio() == null || p.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero");
        }
    }
}
