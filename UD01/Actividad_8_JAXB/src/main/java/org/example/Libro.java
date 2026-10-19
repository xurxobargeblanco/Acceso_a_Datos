package org.example;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

public class Libro {

    private int codigo;
    private String titulo;
    private String autor;
    private double precio;
    private int cantidad;

    public Libro() {}

    public Libro(int codigo, String titulo, String autor, double precio, int cantidad) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    @XmlAttribute(name = "codigo")
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    @XmlElement(name = "titulo")
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    @XmlElement(name = "autor")
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    @XmlElement(name = "precio")
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    @XmlElement(name = "cantidad")
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return "Libro{" +
                "codigo=" + codigo +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }
}
