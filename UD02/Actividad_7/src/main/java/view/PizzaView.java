package view;

import model.dto.PizzaResumenDTO;
import model.entity.Pizza;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class PizzaView {

    private final Scanner sc = new Scanner(System.in);

    public void mostrarMenu(){
        System.out.println("""
                1. Listar todas las pizzas
                2. Insertar una nueva pizza
                3. Actualizar una pizza
                4. Eliminar una pizza
                5. Buscar pizzas
                6. Mostrar un listado resumido
                7. Salir
                """);
    }

    public int pedirOpcion(String opcion) {
        return leerEntero(opcion);
    }

    public int pedirID(){
        return leerEntero("Introduce el ID : ");
    }

    public Pizza pedirDatosPizza() {
        String nombre = leerCadena("Introduce el nombre : ");
        String descripcion = leerCadena("Introduce la descripción : ");
        BigDecimal precio = leerDecimal("Introduce el precio : ");
        boolean disponible = leerCadena("¿Disponible? (S/N) : ").trim().equalsIgnoreCase("S");

        return new Pizza(nombre, descripcion, precio, disponible);
    }

    public String pedirNombreBusqueda(){
        return leerCadena("Introduce o nome deseado : ");
    }

    public void mostrarPizzas(List<Pizza> pizzas){
        if (pizzas.isEmpty()){
            System.out.println("No hay pizzas");
            return;
        }
        for (Pizza p : pizzas) mostrarPizza(p);
    }

    public void mostrarPizza(Pizza p){
        System.out.println(p.getId() + " - " + p.getNombre()
                + " - " + p.getDescripcion()
                + " - " + p.getPrecio() + " €"
                + " - " + (p.isDisponible() ? "Disponible" : "No disponible"));
    }

    public void mostrarResumen(List<PizzaResumenDTO> resumenDTOS){
        if (resumenDTOS.isEmpty()){
            System.out.println("No hay pizzas");
            return;
        }
        for (PizzaResumenDTO dto : resumenDTOS) {
            System.out.println(dto.getNombre() + " - " + dto.getPrecio() + " €");
        }
    }

    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine();
            try {
                return Integer.parseInt(texto.trim());
            } catch (NumberFormatException e) {
                System.out.println("Debes escribir un número.");
            }
        }
    }

    private String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    private BigDecimal leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim().replace(',', '.');
            try {
                return new BigDecimal(texto);
            } catch (NumberFormatException e) {
                System.out.println("Debes escribir un número válido (ej. 8.50).");
            }
        }
    }
}
