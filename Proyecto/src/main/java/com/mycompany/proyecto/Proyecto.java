package com.mycompany.proyecto;

import java.util.Scanner;

public class Proyecto {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String opcion = "";

        while (opcion != "4") {
            System.out.println("""
                               1. Configuraciones
                               2. Nuevo juego
                               3. Reporte
                               4. Salir""");
            opcion = sc.nextLine();
            System.out.println("");
        }
    }
}
