package com.mycompany.proyecto;

import java.util.Scanner;

public class Proyecto {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String opcion = "";

        while (!"4".equals(opcion)) {
            System.out.println("""
                               1. Configuraciones
                               2. Nuevo juego
                               3. Reporte
                               4. Salir""");
            opcion = sc.nextLine().strip();
            System.out.println("");

            switch (opcion) {
                case "1":
                    Configuraciones();
                    break;
                case "2":
                    nuevoJuego();
                    break;
                case "3":
                    Reporte();
                    break;
            }
        }
    }

    public static void Configuraciones() {
        String opcion = "";
        Scanner sc = new Scanner(System.in);
        while (!"4".equals(opcion)) {
            System.out.println("""
                           1. Administrar terminos academicos
                           2. Administrar materias y paralelos
                           3. Administrar preguntas
                           4. Salir""");
            opcion = sc.nextLine().strip();
            System.out.println("");
            switch (opcion) {
                case "1":
                    AdministrarTermino();
                    break;
                case "2":
                    AdministrarMateriaParalelo();
                    break;
                case "3":
                    AdministrarPreguntas();
                    break;
            }
        }

    }

    public static void AdministrarTermino() {
        String opcion = "";
        Scanner sc = new Scanner(System.in);
        while (!"4".equals(opcion)) {
            System.out.println("""
                           1. Ingresar termino 
                           2. Editar termino 
                           3. Configurar termino para el juego
                           4. Salir""");
            opcion = sc.nextLine().strip();
            System.out.println("");
            switch (opcion) {
                case "1":
                    System.out.println("bip bip bup");
                    break;
                case "2":
                    System.out.println("bip bip bup");
                    break;
                case "3":
                    System.out.println("bip bip bup");
                    break;
            }
        }
    }

    public static void AdministrarMateriaParalelo() {
        String opcion = "";
        Scanner sc = new Scanner(System.in);
        while (!"5".equals(opcion)) {
            System.out.println("""
                           1. Ingresar materia
                           2. Editar materia
                           3. Agregar paralelo
                           4. Eliminar paralelo
                           5. Salir""");
            opcion = sc.nextLine().strip();
            System.out.println("");
            switch (opcion) {
                case "1":
                    System.out.println("bip bip bup");
                    break;
                case "2":
                    System.out.println("bip bip bup");
                    break;
                case "3":
                    System.out.println("bip bip bup");
                    break;
                case "4":
                    System.out.println("bip bip bup");
                    break;
            }
        }
    }

    public static void AdministrarPreguntas() {
        String opcion = "";
        Scanner sc = new Scanner(System.in);
        while (!"4".equals(opcion)) {
            System.out.println("""
                           1. Visualizar preguntas
                           2. Agregar pregunta
                           3. Eliminar pregunta
                           4. Salir""");
            opcion = sc.nextLine().strip();
            System.out.println("");
            switch (opcion) {
                case "1":
                    System.out.println("bip bip bup");
                    break;
                case "2":
                    System.out.println("bip bip bup");
                    break;
                case "3":
                    System.out.println("bip bip bup");
                    break;
            }
        }
    }

    public static void nuevoJuego() {
        Juego game = new Juego();
        System.out.println("juego nuevo creado");
    }

    public static void Reporte() {
        System.out.println("trabajando en ello");

    }
}
