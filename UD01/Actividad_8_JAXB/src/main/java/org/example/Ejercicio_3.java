package org.example;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import java.io.File;

public class Ejercicio_3 {

    private static final String FICHEIRO_XML = "libreria_v2.xml";


    public static void main(String[] args){
        escribirXML(FICHEIRO_XML);
    }

    public static void escribirXML(String ruta) {
        File f = new File(ruta);
        if (!f.exists()) {
            System.out.println("O ficheiro XML non existe: " + ruta);
            return;
        }
        try{

        Libro l = new Libro(1,"Don Quijote de la Mancha","Miguel de Cervantes", 23.65,8);

            JAXBContext context = JAXBContext.newInstance(Libro.class);


        } catch (JAXBException e) {
            throw new RuntimeException("Error no marshalling : " + e.getErrorCode());
        }
    }
}
