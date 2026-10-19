package org.example.Tarea_5;

import java.io.*;

//Ejercicio 5
//Escribe un programa capaz de quitar los comentarios de un fichero de Java. Se creará, por ejemplo,
//un fichero con nombre holav2.java que contiene el código de hola.java pero sin los
//comentarios.
public class Tarea_5 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("ficheros/hola.java"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("ficheros/holav2.java"))) {

            boolean comentarioDeBloque = false;
            String linea;

            while ((linea = br.readLine()) != null) {
                String resultado = "";
                int i = 0;

                while (i < linea.length()) {
                    if (!comentarioDeBloque && i + 1 < linea.length() && linea.charAt(i) == '/' && linea.charAt(i + 1) == '*') {
                        comentarioDeBloque = true;
                        i += 2;
                    } else if (comentarioDeBloque && i + 1 < linea.length() && linea.charAt(i) == '*' && linea.charAt(i + 1) == '/') {
                        comentarioDeBloque = false;
                        i += 2;
                    } else if (!comentarioDeBloque && i + 1 < linea.length() && linea.charAt(i) == '/' && linea.charAt(i + 1) == '/') {
                        // Comentario de línea: ignoramos el resto
                        break;
                    } else if (!comentarioDeBloque) {
                        resultado += linea.charAt(i);
                        i++;
                    } else {
                        i++;
                    }
                }

                if (!comentarioDeBloque && resultado.trim().length() > 0) {
                    bw.write(resultado);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
