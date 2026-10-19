import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class Ejercicio_1 {

    private static final String FICHEIRO_DAT = "libreria.xml";

    public static void main(String[] args) {
        lectura();
    }

    public static void lectura(){
        try {

            SAXParserFactory saxpf = SAXParserFactory.newInstance();
            SAXParser saxParser = saxpf.newSAXParser();
            MiHandler handler = new MiHandler();

            saxParser.parse(FICHEIRO_DAT,handler);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

}

class MiHandler extends DefaultHandler {
    private String etiquetaActual = "";

    @Override
    public void startDocument() {
        System.out.println("Inicio del documento");
        System.out.println();
    }

    @Override
    public void endDocument() {
        System.out.println("Fin del documento");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)  {
        etiquetaActual = qName;

        if (qName.equalsIgnoreCase("libro")) {
            System.out.println("Libro codigo: " + attributes.getValue("codigo"));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)  {
        String texto = new String(ch, start, length).trim();
        if (texto.isEmpty()) return;

        switch (etiquetaActual) {
            case "titulo"   -> System.out.println("  Titulo: " + texto);
            case "autor"    -> System.out.println("  Autor: " + texto);
            case "precio"   -> System.out.println("  Precio: " + texto);
            case "cantidad" -> System.out.println("  Cantidad: " + texto);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)  {
        etiquetaActual = "";
        if (qName.equalsIgnoreCase("libro")) System.out.println();
    }
}
