package org.example.Ejercicio_1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;

public class ConvertidorJsonXml {

    void jsonAxml(File jsonFile, File xmlFile){

        ObjectMapper jsonMapper = new ObjectMapper();

        XmlMapper xmlMapper = new XmlMapper();



    }

    void xmlAjson(File xmlFile, File jsonFile){

    }
}
