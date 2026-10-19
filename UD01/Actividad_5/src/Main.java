import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {

    private static final String FICHEIRO = "libreria.dat";
    private static final int TITULO_LONG = 30;
    private static final int AUTOR_LONG = 50;
    private static final int REGISTRO_SIZE = 4 + (TITULO_LONG*2) + (AUTOR_LONG*2) + 8 + 4;
    private static final int O_PRECIO = 4 + (TITULO_LONG * 2) + (AUTOR_LONG * 2);


    public static void main(String[] args){
        menu();
    }

    public static void menu(){
        Scanner sc = new Scanner(System.in);
        boolean acabar = false;

        do {
            System.out.println("MENU DEL PROGRAMA");
            System.out.println("""
                1. Altas
                2. Listado del fichero completo
                3. Consultas
                4. Modificaciones
                0. Salir
                """);

            int seleccion = sc.nextInt();

            switch (seleccion){
                case 1:
                    sc.nextLine();
                    System.out.println("Título :");
                    String titulo = sc.nextLine();
                    System.out.println("Autor :");
                    String autor = sc.nextLine();
                    System.out.println("Precio :");
                    double precio = sc.nextDouble();
                    System.out.println("Cantidad :");
                    int cantidad = sc.nextInt();
                    altas(titulo,autor,precio,cantidad);
                    break;
                case 2:
                    listar();
                    break;
                case 3:

                    System.out.println("Submenu de programa");
                    System.out.println("""
                        3.1 Registro determinado (1)
                        3.2 Libros de autor en stock (2)
                        3.3 Salir (0)
                           \s""");

                    int secundario = sc.nextInt();

                    switch (secundario){
                        case 1:
                            System.out.println("Introduce el código del libro deseado : ");
                            int c = sc.nextInt();
                            busquedaCodigo(c);
                            break;
                        case 2:
                            sc.nextLine();
                            System.out.println("Introduce el autor deseado : ");
                            String a = sc.nextLine();
                            busquedaAutor(a);
                            break;
                        case 0:
                            System.out.println("Saliendo al menu principal");
                            break;
                        default:
                            System.out.println("Error en la selección");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Introduce código :");
                    int c = sc.nextInt();
                    System.out.println("Introduce el precio modificado :");
                    double p = sc.nextDouble();
                    System.out.println("Introduce la cantidad modificada");
                    int ca = sc.nextInt();
                    modificaciones(c,p,ca);
                    break;
                case 0:
                    sc.close();
                    System.out.println("Saliendo ...");
                    acabar = true;
                    break;
                default:
                    System.out.println("Error en la selección");
                    break;
            }
        }while (!acabar);
    }

    private static void escribirTexto(RandomAccessFile raf, String texto, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder(texto);
        sb.setLength(longitud);
        raf.writeChars(sb.toString());
    }

    private static String leerTexto(RandomAccessFile raf, int longitud) throws IOException{
        char [] c = new char[longitud];
        for (int i = 0; i < longitud; i++) {
            c[i] = raf.readChar();
        }
        return new String(c).trim();
    }

    public static void altas(String titulo, String autor, double precio, int cantidad){
        try (RandomAccessFile raf = new RandomAccessFile(FICHEIRO,"rw")){
            int codigo = (int) (raf.length() / REGISTRO_SIZE) + 1;
            raf.seek(raf.length());
            raf.writeInt(codigo);
            escribirTexto(raf,titulo,TITULO_LONG);
            escribirTexto(raf,autor,AUTOR_LONG);
            raf.writeDouble(precio);
            raf.writeInt(cantidad);
            System.out.println("Libro dado de alta con código : " + codigo);
        } catch (IOException e) {
            System.out.println("Error na escritura : " + e.getMessage());
        }
    }

    public static void listar(){
        File f = new File(FICHEIRO);
        if (!f.exists() || f.length() == 0){
            System.out.println("Non hai libros na librería");
            return;
        }
        double total = 0;
        try (RandomAccessFile raf = new RandomAccessFile(f,"r")){
            while (raf.getFilePointer() < raf.length()){
                int codigo = raf.readInt();
                String titulo = leerTexto(raf,TITULO_LONG);
                String autor = leerTexto(raf,AUTOR_LONG);
                double precio = raf.readDouble();
                int cantidad = raf.readInt();
                System.out.println("_".repeat(40));
                System.out.println("-Codigo - " + codigo + "\n" + "-Tiulo - " + titulo + "\n" +
                        "-Autor - " + autor + "\n" + "-Precio - " + precio + "\n" +
                        "-Cantidad - " + cantidad);
                System.out.println("_".repeat(40));
                total += precio * cantidad;
            }
            System.out.println("Valor total de la librería : " + total);
        }catch (IOException e){
            System.out.println("Error na lectura : " + e.getMessage());
        }
    }

    public static void busquedaCodigo(int codigo){
        File f = new File(FICHEIRO);

        if (!f.exists() || f.length() == 0) {
            System.out.println("Non hai libros na librería");
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
            long pos = (long) (codigo - 1) * REGISTRO_SIZE;

            if (codigo < 1 || pos >= raf.length()) {
                System.out.println("Non existe ningún libro co código " + codigo);
                return;
            }

            raf.seek(pos);
            int c = raf.readInt();
            String titulo = leerTexto(raf, TITULO_LONG);
            String autor = leerTexto(raf, AUTOR_LONG);
            double precio = raf.readDouble();
            int cantidad = raf.readInt();

            System.out.println("_".repeat(40));
            System.out.println("-Código - " + c + "\n" + "-Título - " + titulo + "\n" +
                    "-Autor - " + autor + "\n" + "-Prezo - " + precio + "\n" +
                    "-Cantidade - " + cantidad);
            System.out.println("_".repeat(40));
        } catch (IOException e) {
            System.out.println("Error na lectura: " + e.getMessage());
        }
    }

    public static void busquedaAutor(String autor) {
        File f = new File(FICHEIRO);

        if (!f.exists() || f.length() == 0) {
            System.out.println("Non hai libros na librería");
            return;
        }

        int totalRegistros = 0;
        int totalEjemplares = 0;

        try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                int c = raf.readInt();
                String titulo = leerTexto(raf, TITULO_LONG);
                String a = leerTexto(raf, AUTOR_LONG);
                double precio = raf.readDouble();
                int cantidad = raf.readInt();

                if (a.equalsIgnoreCase(autor.trim())) {
                    System.out.println("_".repeat(40));
                    System.out.println("-Código - " + c + "\n" + "-Título - " + titulo + "\n" +
                            "-Autor - " + a + "\n" + "-Prezo - " + precio + "\n" +
                            "-Cantidade - " + cantidad);
                    System.out.println("_".repeat(40));
                    totalRegistros++;
                    totalEjemplares += cantidad;
                }
            }
        } catch (IOException e) {
            System.out.println("Error na lectura: " + e.getMessage());
            return;
        }

        if (totalRegistros == 0) {
            System.out.println("Non hai libros do autor " + autor);
        } else {
            System.out.println("Libros (rexistros) de " + autor + ": " + totalRegistros);
            System.out.println("Exemplares en stock de " + autor + ": " + totalEjemplares);
        }
    }

    public static void modificaciones(int codigo,double precio,int cantidad){
        File f = new File(FICHEIRO);

        if (!f.exists() || f.length() == 0) {
            System.out.println("Non hai libros na librería");
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(f, "rw")) {
            long pos = (long) (codigo - 1) * REGISTRO_SIZE;

            if (codigo < 1 || pos >= raf.length()) {
                System.out.println("Non existe ningún libro co código " + codigo);
                return;
            }

            raf.seek(pos + O_PRECIO);
            raf.writeDouble(precio);
            raf.writeInt(cantidad);
            System.out.println("Libro " + codigo + " modificado correctamente");
        } catch (IOException e) {
            System.out.println("Error na escritura: " + e.getMessage());
        }
    }
}
