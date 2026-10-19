package org.example;

import java.io.*;

public class Ejercicio_1 {
    public static void main(String[] args) {

        byte[] buffer = new byte[8192];

        long tempoInicio = System.currentTimeMillis();

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("orixe.pdf"));
             BufferedOutputStream bus = new BufferedOutputStream(new FileOutputStream("destino.pdf"))) {

            int bytesLeidos;

            while ((bytesLeidos = bis.read(buffer)) != -1) {
                bus.write(buffer, 0, bytesLeidos);
            }

            long tempoFin = System.currentTimeMillis();

            System.out.println("Copia completada con éxito.");
            System.out.println("Tiempo total de ejecución: " + (tempoFin - tempoInicio) + " ms.");

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Non se atopa o arquivo especificado. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro de entrada/saída durante a copia: " + e.getMessage());
        }
    }
}
