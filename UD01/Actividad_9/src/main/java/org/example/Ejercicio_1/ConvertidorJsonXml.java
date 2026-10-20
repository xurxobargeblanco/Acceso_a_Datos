package org.example.Ejercicio_1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class ConvertidorJsonXml {

    private static final String RAIZ = "recetas.json";

    private String leerFichero(File fichero) throws IOException {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        }
        return contenido.toString();
    }

    private void escribirFichero(File fichero, String texto) throws IOException {
        try (FileWriter escritor = new FileWriter(fichero)) {
            escritor.write(texto);
        }
    }

    public void jsonAxml(File jsonFile, File xmlFile) {
        try {
            String jsonString = leerFichero(jsonFile);

            ObjectMapper jsonMapper = new ObjectMapper();
            JsonNode jsonNode = jsonMapper.readTree(jsonString);

            JsonNode contido = jsonNode.get(RAIZ);
            if (contido == null) {
                System.out.println("O JSON non ten o elemento \"" + RAIZ + "\"");
                return;
            }

            XmlMapper xmlMapper = new XmlMapper();
            String xmlOutput = xmlMapper.writerWithDefaultPrettyPrinter()
                    .withRootName(RAIZ)
                    .writeValueAsString(contido);

            escribirFichero(xmlFile, xmlOutput);
            System.out.println("Convertido " + jsonFile.getName() + " -> " + xmlFile.getName());
        } catch (IOException e) {
            System.out.println("Error na conversión Json - XML : " + e.getMessage());
        }
    }

    public void xmlAjson(File xmlFile, File jsonFile) {
        try {
            String xmlString = leerFichero(xmlFile);

            XmlMapper xmlMapper = new XmlMapper();
            JsonNode jsonNode = xmlMapper.readTree(xmlString);

            ObjectMapper jsonMapper = new ObjectMapper();
            String jsonOutput = jsonMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(jsonNode);

            escribirFichero(jsonFile, jsonOutput);
            System.out.println("Convertido " + xmlFile.getName() + " -> " + jsonFile.getName());
        } catch (IOException e) {
            System.out.println("Error na conversión XML - Json : " + e.getMessage());
        }
    }
}
