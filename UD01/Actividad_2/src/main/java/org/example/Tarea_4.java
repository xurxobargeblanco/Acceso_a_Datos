package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//Ejercicio 4
//Realiza un programa que diga cuántas ocurrencias de una palabra hay en un fichero.
public class Tarea_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ocurrencias = 0;
        System.out.println("Que palabra deseas buscar ? ");
        String buscado = sc.nextLine();

        try {
            BufferedReader Br0 = new BufferedReader(new FileReader("archivo.txt"));
                String linea;
            while ((linea = Br0.readLine()) != null){
                if (linea.contains(buscado)){
                    ocurrencias++;
                }
            }
        }catch (IOException e){
            System.out.println("Error en la lectura del arcvhivo : " + e.getMessage());
        }

        System.out.println("La palabra buscada (" + buscado +") tiene " + ocurrencias + " en el archivo");
    }
}
