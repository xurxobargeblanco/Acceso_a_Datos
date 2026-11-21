package controller;

import model.entity.Pizza;
import model.service.PizzaService;
import view.PizzaView;

import java.sql.SQLException;
import java.util.Optional;

public class PizzaController {

    private final PizzaService service;
    private final PizzaView view;

    public PizzaController(PizzaService service, PizzaView view) {
        this.service = service;
        this.view = view;
    }

    public void iniciar() {
        int opcion;
        do {
            view.mostrarMenu();
            opcion = view.pedirOpcion("Elige una opción : ");

            try {
                switch (opcion) {
                    case 1 -> view.mostrarPizzas(service.listarTodas());
                    case 2 -> insertar();
                    case 3 -> actualizar();
                    case 4 -> eliminar();
                    case 5 -> buscar();
                    case 6 -> view.mostrarResumen(service.listarResumido());
                    case 7 -> view.mostrarMensaje("Hasta pronto");
                    default -> view.mostrarMensaje("Opción no válida");
                }
            } catch (IllegalArgumentException e) {
                view.mostrarMensaje(e.getMessage());
            } catch (SQLException e) {
                view.mostrarMensaje("Error de base de datos: " + e.getMessage());
            }
        } while (opcion != 7);
    }

    private void insertar() throws SQLException {
        Pizza pizza = view.pedirDatosPizza();
        service.insertar(pizza);
        view.mostrarMensaje("Pizza insertada correctamente");
    }

    private void actualizar() throws SQLException {
        int id = view.pedirID();
        Pizza pizza = view.pedirDatosPizza();
        pizza.setId(id);
        service.actualizar(pizza);
        view.mostrarMensaje("Pizza actualizada correctamente");
    }

    private void eliminar() throws SQLException {
        int id = view.pedirID();
        service.eliminar(id);
        view.mostrarMensaje("Pizza eliminada correctamente");
    }

    private void buscar() throws SQLException {
        int tipo = view.pedirOpcion("1. Por id  2. Por nombre : ");

        switch (tipo) {
            case 1 -> {
                Optional<Pizza> pizza = service.buscarPorId(view.pedirID());
                if (pizza.isPresent()) {
                    view.mostrarPizza(pizza.get());
                } else {
                    view.mostrarMensaje("No existe ninguna pizza con ese id");
                }
            }
            case 2 -> view.mostrarPizzas(service.buscarPorNombre(view.pedirNombreBusqueda()));
            default -> view.mostrarMensaje("Opción no válida");
        }
    }
}
