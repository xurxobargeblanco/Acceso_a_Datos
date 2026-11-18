package model.dto;

import java.math.BigDecimal;

public class PizzaResumenDTO {
    private String nombre;
    private BigDecimal precio;

    public PizzaResumenDTO(String nome, BigDecimal prezo) {
        this.nombre = nome;
        this.precio = prezo;
    }

    public String getNombre() {return nombre;}
    public BigDecimal getPrecio() {return precio;}

    @Override
    public String toString() {
        return "PizzaResumenDTO{" +
                "nome='" + nombre + '\'' +
                ", prezo=" + precio +
                '}';
    }
}
