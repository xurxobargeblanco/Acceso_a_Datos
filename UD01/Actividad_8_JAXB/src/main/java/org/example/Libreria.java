package org.example;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

@XmlRootElement(name = "libreria")
public class Libreria {

    private ArrayList<Libro> lista = new ArrayList<>();

    public Libreria() {}

    @XmlElement(name = "libro")
    public ArrayList<Libro> getLista() { return lista; }
    public void setLista(ArrayList<Libro> lista) { this.lista = lista; }

    @Override
    public String toString() {
        return "Libreria{" +
                "lista=" + lista +
                '}';
    }
}
