import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Tarea_1 {
    private static final String FICHEIRO_DAT = "libreria.dat";
    private static final int TITULO_LONG = 30;
    private static final int AUTOR_LONG = 50;
    private static final int REGISTRO_SIZE = 4 + (TITULO_LONG * 2) + (AUTOR_LONG * 2) + 8 + 4;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Deseas crear un novo registro no .dat? (S/N)");
        if (sc.nextLine().equalsIgnoreCase("S")) {
            crearFicheroDat(sc);
        }

        System.out.println("\nDeseas exportar o ficheiro .dat a XML? (S/N)");
        if (sc.nextLine().equalsIgnoreCase("S")) {
            datAXml(FICHEIRO_DAT, "libreria.xml");
        }

        System.out.println("\nDeseas ler e amosar o XML creado? (S/N)");
        if (sc.nextLine().equalsIgnoreCase("S")) {
            lecturaXML("libreria.xml");
        }

        sc.close();
    }

    // LÓGICA DE PARSEO A XML E LECTURA

    public static void datAXml(String ficheiroDat, String ficheiroXml) {
        File f = new File(ficheiroDat);
        if (!f.exists() || f.length() == 0) {
            System.out.println("O ficheiro .dat non existe ou está baleiro. Primeiro crea datos!");
            return;
        }

        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element raiz = doc.createElement("libreria");
            doc.appendChild(raiz);

            try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
                while (raf.getFilePointer() < raf.length()) {
                    int codigo = raf.readInt();
                    String titulo = leerTexto(raf, TITULO_LONG);
                    String autor = leerTexto(raf, AUTOR_LONG);
                    double precio = raf.readDouble();
                    int cantidad = raf.readInt();

                    // etiqueta <libro>
                    Element libro = doc.createElement("libro");
                    libro.setAttribute("codigo", String.valueOf(codigo));

                    // <titulo>
                    Element elemTitulo = doc.createElement("titulo");
                    elemTitulo.appendChild(doc.createTextNode(titulo));
                    libro.appendChild(elemTitulo);

                    // <autor>
                    Element elemAutor = doc.createElement("autor");
                    elemAutor.appendChild(doc.createTextNode(autor));
                    libro.appendChild(elemAutor);

                    // <precio>
                    Element elemPrecio = doc.createElement("precio");
                    elemPrecio.appendChild(doc.createTextNode(String.valueOf(precio)));
                    libro.appendChild(elemPrecio);

                    // <cantidad>
                    Element elemCantidad = doc.createElement("cantidad");
                    elemCantidad.appendChild(doc.createTextNode(String.valueOf(cantidad)));
                    libro.appendChild(elemCantidad);

                    raiz.appendChild(libro);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(ficheiroXml));

            transformer.transform(source, result);

            System.out.println("Ficheiro XML xerado con éxito: " + ficheiroXml);

        } catch (ParserConfigurationException ex) {
            System.err.println("Erro ao configurar o parser DOM.");
        } catch (TransformerException ex) {
            System.err.println("Erro ao transformar a árbore DOM a XML.");
        } catch (IOException ex) {
            System.err.println("Erro de lectura no ficheiro .dat: " + ex.getMessage());
        }
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

            System.out.println("Ficheiro XML parseado correctamente.");
            System.out.println("Elemento raíz: " + doc.getDocumentElement().getNodeName());

        } catch (ParserConfigurationException ex) {
            System.err.println("Erro na configuración do parser.");
        } catch (IOException | SAXException e) {
            System.err.println("Erro ao parsear o ficheiro XML: " + e.getMessage());
        }
    }

    // LÓGICA DE FICHEIRO ALEATORIO (.DAT)

    public static void crearFicheroDat(Scanner sc) {
        System.out.println("Título:");
        String titulo = sc.nextLine();
        System.out.println("Autor:");
        String autor = sc.nextLine();
        System.out.println("Precio:");
        double precio = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
        System.out.println("Cantidad:");
        int cantidad = Integer.parseInt(sc.nextLine().trim());

        altas(titulo, autor, precio, cantidad);
        listar();
    }

    private static void escribirTexto(RandomAccessFile raf, String texto, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder(texto);
        sb.setLength(longitud);
        raf.writeChars(sb.toString());
    }

    private static String leerTexto(RandomAccessFile raf, int longitud) throws IOException {
        char[] c = new char[longitud];
        for (int i = 0; i < longitud; i++) {
            c[i] = raf.readChar();
        }
        return new String(c).trim();
    }

    public static void altas(String titulo, String autor, double precio, int cantidad) {
        try (RandomAccessFile raf = new RandomAccessFile(FICHEIRO_DAT, "rw")) {
            int codigo = (int) (raf.length() / REGISTRO_SIZE) + 1;
            raf.seek(raf.length());
            raf.writeInt(codigo);
            escribirTexto(raf, titulo, TITULO_LONG);
            escribirTexto(raf, autor, AUTOR_LONG);
            raf.writeDouble(precio);
            raf.writeInt(cantidad);
            System.out.println("Libro dado de alta con código: " + codigo);
        } catch (IOException e) {
            System.out.println("Error na escritura: " + e.getMessage());
        }
    }

    public static void listar() {
        File f = new File(FICHEIRO_DAT);
        if (!f.exists() || f.length() == 0) {
            System.out.println("Non hai libros na librería");
            return;
        }
        double total = 0;
        try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                int codigo = raf.readInt();
                String titulo = leerTexto(raf, TITULO_LONG);
                String autor = leerTexto(raf, AUTOR_LONG);
                double precio = raf.readDouble();
                int cantidad = raf.readInt();

                System.out.println("_".repeat(40));
                System.out.println("- Código: " + codigo + "\n" +
                        "- Título: " + titulo + "\n" +
                        "- Autor:  " + autor + "\n" +
                        "- Precio: " + precio + " €\n" +
                        "- Cantid: " + cantidad);
                System.out.println("_".repeat(40));

                total += precio * cantidad;
            }
            System.out.println("Valor total de la librería: " + total + " €");
        } catch (IOException e) {
            System.out.println("Error na lectura: " + e.getMessage());
        }
    }
}