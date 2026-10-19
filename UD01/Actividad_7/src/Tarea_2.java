import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Tarea_2 {
    private static final String FICHEIRO_XML = "libreria.xml";

    public static void main(String[] args){
        lecturaXML(FICHEIRO_XML);
    }

    private static String texto(Element padre, String etiqueta) {
        NodeList lista = padre.getElementsByTagName(etiqueta);
        if (lista.getLength() == 0) {
            return "";
        }
        return lista.item(0).getTextContent().trim();
    }

    public static void lecturaXML(String rutaFicheroXml) {
        File f = new File(rutaFicheroXml);
        if (!f.exists()) {
            System.out.println("O ficheiro XML non existe: " + rutaFicheroXml);
            return;
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(f);

            doc.getDocumentElement().normalize();

            NodeList libros = doc.getElementsByTagName("libro");

            System.out.println("Librería :");
            for (int i = 0; i < libros.getLength(); i++) {
                Node nodo = libros.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element libro = (Element) nodo;

                    System.out.println("    Libro:");
                    System.out.println("        Código: " + libro.getAttribute("codigo"));
                    System.out.println("        Título: " + texto(libro, "titulo"));
                    System.out.println("        Autor: " + texto(libro, "autor"));
                    System.out.println("        Precio: " + texto(libro, "precio") + " €");
                    System.out.println("        Cantidad: " + texto(libro, "cantidad"));
                }
            }

        } catch (ParserConfigurationException ex) {
            System.err.println("Erro na configuración do parser.");
        } catch (IOException | SAXException e) {
            System.err.println("Erro ao parsear o ficheiro XML: " + e.getMessage());
        }
    }
}
