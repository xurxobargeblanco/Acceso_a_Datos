package Ejercicio_2;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main_2 {

    private static final String FICHEIRO = "libros.dat";

    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        Scanner sc = new Scanner(System.in);
        boolean acabar = false;

        do {
            System.out.println("MENU DEL PROGRAMA");
            System.out.println("""
                1. Insertar nuevo libro
                2. Listar libros guardados
                3. Consultar libro
                0. Salir
                """);
            int seleccion = sc.nextInt();

            switch (seleccion){
                case 1:
                    sc.nextLine();
                    System.out.println("Introduce en titulo del libro");
                    String t = sc.nextLine();

                    System.out.println("Introduce el autor del libro " + t);
                    String a = sc.nextLine();

                    System.out.println("Introduce el isbn de " + t);
                    String isbn = sc.nextLine();

                    System.out.println("Introduce el precio de " + t);
                    int p = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Introduce el stock de " + t);
                    int o = sc.nextInt();
                    anadirLibro(t,a,isbn,p,o);
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    sc.nextLine();
                    System.out.println("Introduce el isbn del libro");
                    String i = sc.nextLine();
                    consultarLibro(i);
                    break;
                case 0:
                    acabar = true;
                    break;
                default:
                    System.out.println("Error en la selección");
                    break;
            }
        }while (!acabar);
        sc.close();
    }

    // Carga los objetos del archivo en el array y devuelve el array.
    private static ArrayList<Libro> leerLibros() {
        File f = new File(FICHEIRO);
        if (!f.exists() || f.length() == 0) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (ArrayList<Libro>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Carga todos los objetos en memoria y sobreescribe el fichero
    public static void anadirLibro(String titulo, String autor, String isbn, double precio, int stock) {
        ArrayList<Libro> datos = leerLibros();
        datos.add(new Libro(titulo, autor, isbn, precio, stock));

        // Se sobrescribe el fichero.
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHEIRO))) {
            oos.writeObject(datos);
            System.out.println("Libro serializado (escrito correctamente)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Muestra todos los libros guardados.
    public static void listarLibros() {
        ArrayList<Libro> datos = leerLibros();
        if (datos.isEmpty()) {
            System.out.println("No hay libros guardados");
            return;
        }
        for (Libro l : datos) {
            System.out.println(l);
        }
    }

    // Consulta un libro a partir del isbn.
    public static void consultarLibro(String isbn) {
        for (Libro l : leerLibros()) {
            if (l.getIsbn().equals(isbn)) {
                System.out.println(l);
                return;
            }
        }
        System.out.println("No existe ningún libro con isbn " + isbn);
    }
}
