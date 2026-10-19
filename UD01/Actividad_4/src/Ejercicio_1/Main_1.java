package Ejercicio_1;

import java.io.*;
import java.util.Scanner;

public class Main_1 {
    private static final String FICHEIRO = "libros.dat";

    // A true para comprobar o problema da cabecera:
    // ao añadir con un ObjectOutputStream normal, o fichero queda corrupto
    // e ao listar salta StreamCorruptedException.

    private static final boolean PROBAR_PROBLEMA_CABECERA = false;

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
    }

    // Función que permite añadir libros a un fichero binario.
    public static void anadirLibro(String titulo, String autor, String isbn, double precio, int stock){
        Libro l = new Libro(titulo, autor, isbn, precio, stock);
        File f = new File(FICHEIRO);
        boolean existeConDatos = f.exists() && f.length() > 0;

        try (FileOutputStream fos = new FileOutputStream(f, true); ObjectOutputStream oos = (existeConDatos && !PROBAR_PROBLEMA_CABECERA)
                     ? new MiObjetoOutputStream(fos) : new ObjectOutputStream(fos)) {
            oos.writeObject(l);
            System.out.println("Libro serializado (escrito correctamente)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Función que permite mostrar todos los libros guardados.
    public static void listarLibros(){
        File f = new File(FICHEIRO);
        if (!f.exists() || f.length() == 0) {
            System.out.println("No hay libros guardados");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                Libro l = (Libro) ois.readObject();
                System.out.println(l);
            }
        } catch (EOFException e) {
            // Fin
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Función que permite consultar un libro a partir del isbn.
    public static void consultarLibro(String isbn){
        File f = new File(FICHEIRO);
        if (!f.exists() || f.length() == 0) {
            System.out.println("No hay libros guardados");
            return;
        }

        boolean encontrado = false;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (!encontrado) {
                Libro l = (Libro) ois.readObject();
                if (l.getIsbn().equals(isbn)) {
                    System.out.println(l);
                    encontrado = true;
                }
            }
        } catch (EOFException e) {
            //Fin
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (!encontrado) System.out.println("No existe ningún libro con isbn " + isbn);
    }
}