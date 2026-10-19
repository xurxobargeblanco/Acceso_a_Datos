package org.example;

import java.io.*;
import java.util.Scanner;

// Ejercicio 3
//Escribe un programa que guarde en un fichero el contenido de otros dos ficheros, de tal forma
//que en el fichero resultante aparezcan mezcladas las líneas de los primeros dos ficheros, es decir,
//la primera línea será del primer fichero, la segunda será del segundo fichero, la tercera será la
//siguiente del primer fichero, etc.
//Los nombres de los dos ficheros origen y el nombre del fichero destino se deben pasar como
//argumentos en la línea de comandos.
//Hay que tener en cuenta que los ficheros de donde se van cogiendo las líneas pueden tener
//tamaños diferentes.

public class Tarea_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (args.length < 3) {
            System.out.println("Debes indicar 3 argumentos: orixe1, orixe2 e destino.");
            return;
        }

        String A1 = args[0];
        String A2 = args[1];
        String A3 = args[2];

        try {
            BufferedReader Bf0 = new BufferedReader(new FileReader(A1));
            BufferedReader Bf1 = new BufferedReader(new FileReader(A2));
            BufferedWriter Bw0 = new BufferedWriter(new FileWriter(A3));

            String linea1 = Bf0.readLine();
            String linea2 = Bf1.readLine();

            while (linea1 != null || linea2 != null) {

                if (linea1 != null) {
                    Bw0.write(linea1);
                    Bw0.newLine();
                    linea1 = Bf0.readLine();
                }

                if (linea2 != null) {
                    Bw0.write(linea2);
                    Bw0.newLine();
                    linea2 = Bf1.readLine();
                }
            }

            Bf0.close(); Bf1.close();Bw0.close(); sc.close();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
