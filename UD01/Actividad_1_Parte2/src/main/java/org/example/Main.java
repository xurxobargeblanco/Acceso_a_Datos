package org.example;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        // 15. Copiar un ficheiro

        File origen = new File("ejercicioFinal.txt");
        File destinoDir = new File("subdir");

        try {
            FileUtils.copyFileToDirectory(origen, destinoDir);
            File ficheroCopiado = new File(destinoDir, origen.getName());

            if (ficheroCopiado.exists()) {
                System.out.println("La copia se ha realizado correctamente.");
                System.out.println("Fichero copiado en: " + ficheroCopiado.getAbsolutePath());
            } else System.out.println("Error: El fichero no se encuentra en el destino.");


        } catch (IOException e) {
            System.err.println("Ocurrió un error al copiar el fichero: " + e.getMessage());
        }

        // 16. Mover un ficheiro

        // 1. Definir directorios origen y destino
        File dirOrigen = new File("ejercicios");
        File dirDestino = new File("subdir");

        // 2. Buscar ficheros .pdf en el directorio origen
        File[] pdfs = dirOrigen.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        if (pdfs != null && pdfs.length > 0) {
            // Seleccionamos el primer fichero .pdf encontrado
            File ficheroAMover = pdfs[0];
            String nombreFichero = ficheroAMover.getName();

            // Referencias para las comprobaciones posteriores
            File ficheroEnOrigen = new File(dirOrigen, nombreFichero);
            File ficheroEnDestino = new File(dirDestino, nombreFichero);

            try {
                // 3. Mover el fichero al directorio destino
                // createDestDir = true crea la carpeta destino si no existe
                FileUtils.moveFileToDirectory(ficheroAMover, dirDestino, true);

                // 4. Comprobar las dos condiciones solicitadas
                boolean yaNoEstaEnOrigen = !ficheroEnOrigen.exists();
                boolean estaEnDestino = ficheroEnDestino.exists();

                if (yaNoEstaEnOrigen && estaEnDestino) {
                    System.out.println("El fichero '" + nombreFichero + "' se ha movido correctamente.");
                    System.out.println("Confirmación: Ya no existe en 'ejercicios' y se encuentra en 'subdir'.");
                } else {
                    System.out.println("Error en las comprobaciones tras mover el fichero.");
                }

            } catch (IOException e) {
                System.err.println("Ocurrió un error al mover el fichero: " + e.getMessage());
            }

        } else {
            System.out.println("No se encontró ningún fichero .pdf en el directorio 'ejercicios'.");
        }
    }
}

