import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarea_1 {
    public static void main(String[] args) {

        // 1. Crear directorio "ejercicios"

        File directorio = new File("ejercicios");

        if (directorio.exists()){
            System.out.println("O directorio 'ejercicios' xa estaba creado.");
        }else {
            if (directorio.mkdir()){
                System.out.println("Directorio 'ejercicios' creado con exito.");
            }else {
                System.out.println("Non se puido crear o directorio 'ejercicios'.");
            }
        }

        // 2. Crear un ficheiro "ejercicio1.txt" dentro do directorio

        File ficheiro = new File(directorio, "ejercicio1.txt");

        try{
            if (ficheiro.exists()){
                System.out.println("O ficheiro 'ejercicio1.txt' xa existe.");
            }else {
                if (ficheiro.createNewFile()){
                    System.out.println("Ficheiro 'ejercicio1.txt' creado correctamente");
                }else {
                    System.out.println("Non se puido crear o ficheiro.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }

        // 3. Información do ficheiro:

        System.out.println("\n--- INFORMACIÓN DO FICHEIRO ---");
        if (ficheiro.exists()) {
            try{
                System.out.println("Nome: " + ficheiro.getName());
                System.out.println("Ruta relativa: " + ficheiro.getPath());
                System.out.println("Ruta absoluta: " + ficheiro.getAbsolutePath());
                System.out.println("Ruta do directorio padre: " + ficheiro.getParent());
                System.out.println("Pódese escribir?: " + (ficheiro.canWrite() ? "Si" : "Non"));
                System.out.println("Pódese ler?: " + (ficheiro.canRead() ? "Si" : "Non"));
                System.out.println("Tamaño: " + Files.size(ficheiro.toPath()) + " bytes");
            }catch (IOException e){
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("Non se pode mostrar información porque o ficheiro non existe.");
        }

        // 4. Crear outro ficheiro

        File ficheiro2 = new File(directorio, "ejercicio2.txt");

        try{
            if (ficheiro2.exists()){
                System.out.println("O ficheiro 'ejercicio2.txt' xa existe.");
            }else {
                if (ficheiro2.createNewFile()){
                    System.out.println("Ficheiro 'ejercicio2.txt' creado correctamente");
                }else {
                    System.out.println("Non se puido crear o ficheiro.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }

        // 5. Crear un subdirectorio e un ficheiro

        File subdirectorio = new File(directorio, "subdir");

        File ficheiro3 = new File(subdirectorio, "subejemplo.txt");

        if (subdirectorio.exists()){
            System.out.println("O directorio 'subdir' xa estaba creado.");
            try{
                if (ficheiro3.exists()){
                    System.out.println("O ficheiro 'subejemplo.txt' xa existe.");
                }else {
                    if (ficheiro3.createNewFile()){
                        System.out.println("Ficheiro 'subejemplo.txt' creado correctamente");
                    }else {
                        System.out.println("Non se puido crear o ficheiro.");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error : " + e.getMessage());
            }
        }else {
            if (subdirectorio.mkdir()){
                System.out.println("Directorio 'subdir' creado con exito.");
                try{
                    if (ficheiro3.exists()){
                        System.out.println("O ficheiro 'subejemplo.txt' xa existe.");
                    }else {
                        if (ficheiro3.createNewFile()){
                            System.out.println("Ficheiro 'subejemplo.txt' creado correctamente");
                        }else {
                            System.out.println("Non se puido crear o ficheiro.");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error : " + e.getMessage());
                }
            }else {
                System.out.println("Non se puido crear o directorio 'subdir'.");
            }
        }

        // 6. Renombrar un ficheiro

        File subejemploCopia = new File("subejemplo_copia.txt");

        try{
            if (ficheiro3.renameTo(subejemploCopia)){
                System.out.println("Ficheiro 'suejemplo.txt' renombrado a " + subejemploCopia.getName() + " con exito");
            }else System.out.println("Non se pudo renombrar o ficheiro 'subejemplo.txt' ");
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }

        // 7. Crear varios ficheiros mediante un bucle

        for (int i = 0; i < 5; i++) {

            File f = new File("ej" + i + ".pdf");

            try {
                if (f.exists()){
                    System.out.println("O ficheiro 'ejercicio1.txt' xa existe.");
                }else {
                    if (f.createNewFile()){
                        System.out.println("Ficheiro 'ejercicio1.txt' creado correctamente");
                    }else {
                        System.out.println("Non se puido crear o ficheiro.");
                    }
                }
            }catch (IOException e){
                System.out.println("Error en creacion de ficheiro " + f.getName() + " : " + e.getMessage());
            }
        }

        // 8. Listar el contenido de un directorio

        File [] listadoDirectorios = directorio.listFiles();

        if (listadoDirectorios != null){
            for (File f : listadoDirectorios){
                if (f.isFile()) {
                    System.out.println("Ficheiro: " + f.getName());
                } else if (f.isDirectory()) {
                    System.out.println("Directorio: " + f.getName());
                }
            }
        }

        // 9. Filtrar ficheiros por extensión

        File [] ficheirosPdf = directorio.listFiles((dir,name) -> name.toLowerCase().endsWith(".pdf"));

        if (ficheirosPdf != null){
            for (File f : ficheirosPdf){
                System.out.println("Ficheiro pdf : " + f.getName());
            }
        }

        // 10. Eliminar un ficheiro

        File archivoABorrar = new File("ejercicios", "ejercicio1.txt");

        if (archivoABorrar.exists()) {
            if (archivoABorrar.delete()) {
                System.out.println("Archivo eliminado con éxito.");
            } else {
                System.out.println("No se pudo eliminar el archivo.");
            }
        } else {
            System.out.println("El archivo no existe.");
        }

        // 11. Comprobar la eliminación

        File [] listadoComprobacion = directorio.listFiles();

        if (listadoComprobacion != null){
            for (File f : listadoComprobacion){
                if (f.getName().equalsIgnoreCase("ejercicio1.txt")) {
                    System.out.println("Ficheiro: " + f.getName() + " sigue sin eliminarse");
                } else{
                    System.out.println("Comprobación correcta");
                }
            }
        }

        // 12.Intentar eliminar un ficheiro que non existe

        File archivoABorrarSin = new File("ejercicios", "ejercicio1.txt");

        if (archivoABorrarSin.exists()) {
            if (archivoABorrarSin.delete()) {
                System.out.println("Archivo eliminado con éxito.");
            } else {
                System.out.println("No se pudo eliminar el archivo.");
            }
        } else {
            System.out.println("El archivo no existe, no se puede borrar.");
        }

        // 13. Consultar la fecha de modificación

        File archivoModificadoHora = new File("ejercicio2CopiaMod.txt");

        if (archivoModificadoHora.exists()) {

            long milisegundos = archivoModificadoHora.lastModified();

            Date fecha = new Date(milisegundos);

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            System.out.println("Última modificación: " + formato.format(fecha));
        } else {
            System.out.println("O archivo a consultar non existe");
        }

        // 14. Renombrar un ficheiro.

        File ejercicioCopia = new File("ejercicioFinal.txt");

        try{
            if (ficheiro2.renameTo(ejercicioCopia)){
                System.out.println("Ficheiro 'ejercicio2.txt' renombrado a " + ejercicioCopia.getName() + " con exito");
            }else System.out.println("Non se pudo renombrar o ficheiro 'ejercicio2.txt' ");
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }
}
