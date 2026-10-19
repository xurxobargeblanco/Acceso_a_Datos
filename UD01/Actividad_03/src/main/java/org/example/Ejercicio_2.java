package org.example;

import java.io.*;
import java.util.Scanner;

public class Ejercicio_2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num ;

        // Escritura en archivo binario dos numeros.

        try (DataOutputStream DOS = new DataOutputStream(new FileOutputStream("Archivo.bin"))){

            do {
                System.out.println("Introduce números....(Introduce negativo para parar)");
                num = sc.nextInt();
                DOS.writeInt(num);
            }while (num > 0);

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        // Lectura dos numeros do archivo binario

        int suma = 0;
        int contador = 0;

        try (DataInputStream DIS = new DataInputStream(new FileInputStream("Archivo.bin"))) {

            while (true) {
                int numero = DIS.readInt();
                System.out.println("Número lido: " + numero);
                suma += numero;
                contador++;
            }

        } catch (EOFException e) {
            System.out.println("Fin da lectura do ficheiro.");
        } catch (IOException e) {
            System.out.println("Erro de E/S: " + e.getMessage());
        }

        System.out.println("Media dos numero leídos : " + suma/contador);
    }
}
