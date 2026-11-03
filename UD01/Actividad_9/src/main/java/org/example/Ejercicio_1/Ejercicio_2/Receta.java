package org.example.Ejercicio_1.Ejercicio_2;

import java.util.ArrayList;

public class Receta {
    private String nombre;
    private int tiempo;
    private ArrayList<String> ingredientes;

    public Receta(String nombre, int tiempo, ArrayList<String> ingredientes) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        return "Receta  : " +
                "nombre='" + nombre + '\'' +
                ", tiempo=" + tiempo +
                ", ingredientes= ( " + ingredientes + " )";
    }
}
