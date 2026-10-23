package org.example.Ejercicio_1.Ejercicio_1;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ConvertidorJsonXml convertidor = new ConvertidorJsonXml();

        File xmlOrixinal = new File("recetas.xml");
        File jsonOrixinal = new File("recetas.json");

        File jsonGenerado = new File("recetas_desde_xml.json");
        File xmlGenerado = new File("recetas_desde_json.xml");

        if (!xmlOrixinal.exists() || !jsonOrixinal.exists()) {
            System.out.println("Faltan recetas.xml ou recetas.json na raíz do proxecto");
            return;
        }

        System.out.println("XML -> JSON");
        convertidor.xmlAjson(xmlOrixinal, jsonGenerado);

        System.out.println("JSON -> XML");
        convertidor.jsonAxml(jsonOrixinal, xmlGenerado);
    }
}
