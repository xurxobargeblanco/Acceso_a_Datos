package org.example;
import java.io.*;
// Ejercicio 1
//Escribe un programa que guarde en un fichero con nombre primos.dat los números primos que
//hay entre 1 y 500.

public class Tarea_1 {
    public static void main(String[] args) {

        File fichero = new File("primos.dat");

        if (fichero.exists()) {
            System.out.println("O ficheiro 'primos.dat' xa existe.");
        } else {
            try {

                if (fichero.createNewFile()) System.out.println("Ficheiro 'primos.dat' creado correctamente.");
                else System.out.println("Non se puido crear o ficheiro.");

            } catch (IOException e) {
                System.out.println("Erro ao crear o ficheiro: " + e.getMessage());
            }
        }

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fichero)))) {

            int contador = 0;

            for (int i = 1; i <= 500; i++) {
                if (esPrimo(i)) {
                    pw.println(i);
                    contador++;
                }
            }

            System.out.println("Proceso finalizado. Gardáronse " + contador + " números primos.");

        } catch (IOException e) {
            System.out.println("Erro ao escribir no ficheiro: " + e.getMessage());
        }
    }
    static boolean esPrimo(int numero) {
        if (numero <= 1) return false;
        int limite = (int) Math.sqrt(numero);
        for (int i = 2; i <= limite; i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }
}
