package org.example;

import java.io.*;
import java.util.Scanner;

public class Ejercicio_3 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean acabar = false;
        int codigo, contador = 0;
        String nombre;
        double precio, suma = 0.0;

        // Escritura de datos pasados por el usuario

        try (DataOutputStream DOS = new DataOutputStream(new FileOutputStream("Archivo2.bin"))){
            do {
                System.out.println("--___ESCRITURA DE PRODUCTOS___--");

                System.out.println("Escribe el código del producto : ");
                codigo = sc.nextInt();
                sc.nextLine();

                System.out.println("Escribe el nombre del producto : ");
                nombre = sc.nextLine();

                System.out.println("Escribe el precio del producto : ");
                precio = sc.nextDouble();
                sc.nextLine();

                DOS.writeInt(codigo);
                DOS.writeUTF(nombre);
                DOS.writeDouble(precio);

                contador ++;

                System.out.println("Quieres escribir otro producto ? (S/N)");
                String respuesta = sc.nextLine();

                if (respuesta.equalsIgnoreCase("N")) acabar = true;

            }while (!acabar);

        } catch (IOException e) {
            System.out.println("Error na escritura : " + e.getMessage());
        }

        // Lectura de datos del archivo

        try (DataInputStream DIS = new DataInputStream(new FileInputStream("Archivo2.bin"))){
            for (int i = 0; i < contador; i++) {
                int codigoR = DIS.readInt();
                String nombreR = DIS.readUTF();
                double precioR = DIS.readDouble();
                suma += precioR;

                System.out.println("Producto nº : " + i + 1 + "\n" + "Código : " + codigoR + "\n" + "Nombre : " + nombreR + "\n" + "Precio : " + precioR);
            }
            System.out.println("Suma de todos os productos : " + suma);
        }catch (EOFException e) {
            System.out.println("Fin da lectura do ficheiro.");
        } catch (IOException e) {
            System.out.println("Erro de E/S: " + e.getMessage());
        }
    }
}
