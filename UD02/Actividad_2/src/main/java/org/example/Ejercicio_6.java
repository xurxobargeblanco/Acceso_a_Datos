package org.example;

import java.util.Scanner;

public class Ejercicio_6 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String Dnombre = pedirTexto(sc,"Nombre de departamento :");
        selectDepartamento(Dnombre);

    }


    private static String pedirTexto(Scanner sc, String mensaxe){
        System.out.println(mensaxe);
        return sc.nextLine();
    }

    public static void selectDepartamento(String Dnombre){
        String sql = "SELECT FROM empleados WHERE DNOMBRE IS " + Dnombre;
    }
}
