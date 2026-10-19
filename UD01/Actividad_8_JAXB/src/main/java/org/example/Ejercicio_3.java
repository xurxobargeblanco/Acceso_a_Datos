package org.example;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;

public class Ejercicio_3 {

    private static final String FICHEIRO_XML = "libreria_v2.xml";

    public static void main(String[] args) {
        escribirXML(FICHEIRO_XML);
    }

    public static void escribirXML(String ruta) {
        Libreria libreria = new Libreria();
        libreria.getLista().add(new Libro(1, "Don Quijote de la Mancha", "Miguel de Cervantes Saavedra", 23.65, 8));
        libreria.getLista().add(new Libro(2, "Asesinato en el Orient Express", "Agatha Christie", 22.60, 5));

        try {
            JAXBContext context = JAXBContext.newInstance(Libreria.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(libreria, System.out);
            marshaller.marshal(libreria, new File(ruta));

            System.out.println("Ficheiro XML creado: " + ruta);
        } catch (JAXBException e) {
            System.out.println("Erro no marshalling: " + e.getMessage());
        }
    }
}
