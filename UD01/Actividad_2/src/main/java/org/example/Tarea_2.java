package org.example;

import java.io.*;

// Ejercicio 2
//Realiza un programa que lea el fichero creado en el ejercicio anterior y que muestre los números
//por pantalla.
public class Tarea_2 {
    public static void main(String[] args) {

        try (BufferedReader lector = new BufferedReader(new FileReader("primos.dat"))) {

            String linea;

            while ((linea = lector.readLine()) != null) System.out.println(linea);

        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }
}
