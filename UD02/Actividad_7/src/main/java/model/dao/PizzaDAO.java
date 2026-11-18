package model.dao;

import model.dto.PizzaResumenDTO;
import model.entity.Pizza;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PizzaDAO {

    public static List<Pizza> listarTodas() throws SQLException {

    }

    public static Optional<Pizza> buscarPorId(int id) throws SQLException{

    }

    public static List<Pizza> buscarPorNome(String nome) throws SQLException{

    }

    public static boolean insertar(Pizza p) throws SQLException{

    }

    public static boolean actualizar(Pizza p) throws SQLException{}

    public static boolean eliminar(int id) throws SQLException{}

    public static List<PizzaResumenDTO> listardoResumido() throws SQLException{

    }
}
