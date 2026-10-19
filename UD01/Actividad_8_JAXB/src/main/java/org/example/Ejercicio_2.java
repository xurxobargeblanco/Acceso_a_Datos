package org.example;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class Ejercicio_2 {

    private static final String FICHEIRO_XML = "libreria.xml";

    public static void main(String[] args) {
        leerXML(FICHEIRO_XML);
    }

    public static void leerXML(String ruta){
        File f = new File(ruta);
        if (!f.exists()) {
            System.out.println("O ficheiro XML non existe: " + ruta);
            return;
        }

        try {
            JAXBContext contexto = JAXBContext.newInstance(Libreria.class);

            Unmarshaller unmarshaller = contexto.createUnmarshaller();

            Libreria libreria = (Libreria) unmarshaller.unmarshal(f);

            System.out.println("Lista de libros:");
            for (Libro l : libreria.getLista()) {
                System.out.println("    Libro:");
                System.out.println("        Código: " + l.getCodigo());
                System.out.println("        Título: " + l.getTitulo());
                System.out.println("        Autor: " + l.getAutor());
                System.out.println("        Precio: " + l.getPrecio());
                System.out.println("        Cantidad: " + l.getCantidad());
            }
        } catch (JAXBException e) {
            System.out.println("Erro no unmarshalling: " + e.getMessage());
        }
    }
}
