package app;

import controller.PizzaController;
import model.dao.PizzaDAOImpl;
import model.service.PizzaService;
import model.service.PizzaServiceImpl;
import model.util.ConnectioPool;
import view.PizzaView;

import java.sql.SQLException;

public class Pizzeria {

    public static void main(String[] args) {
        PizzaService service = new PizzaServiceImpl(new PizzaDAOImpl());
        PizzaView view = new PizzaView();
        PizzaController controller = new PizzaController(service, view);

        try {
            controller.iniciar();
        } finally {
            try {
                ConnectioPool.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el pool: " + e.getMessage());
            }
        }
    }
}
