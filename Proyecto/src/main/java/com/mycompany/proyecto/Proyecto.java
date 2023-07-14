package com.mycompany.proyecto;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Proyecto {

    public static void main(String[] args) {

        Configuracion.CargarInformacion();
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
                    Configuracion.mostrarParalelos();
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
                    Configuracion.ingresarMateria();
                    break;
                case "2":
                    Configuracion.editarMateria();
                    break;
                case "3":
                    Configuracion.ingresarParalelo();
                    break;
                case "4":
                    Configuracion.eliminarParalelo();
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Por favor seleccione la materia: ");
        for (int i = 0; i < Configuracion.materias.size(); i++) {
            System.out.println((i + 1) + ". " + Configuracion.materias.get(i));
        }
        int opcion = Integer.parseInt(sc.nextLine().strip()) - 1;
        Materia ma = Configuracion.materias.get(opcion);

        ArrayList<Paralelo> paralelos = new ArrayList<>();
        for (Paralelo pa : Configuracion.paralelos) {
            if (pa.getMateria() == ma) {
                paralelos.add(pa);
            }
        }
        System.out.println("");
        System.out.println("Por favor seleccione el Paralelo: ");
        for (int i = 0; i < paralelos.size(); i++) {
            System.out.println((i + 1) + ". " + paralelos.get(i).getNumeroParalelo());
        }
        opcion = Integer.parseInt(sc.nextLine().strip()) - 1;
        Paralelo pa = paralelos.get(opcion);
        System.out.println("");
        System.out.println("Por favor seleccione al participante: ");
        System.out.println("""
                           1. Ingresar matricula
                           2. Escoger Aleatorio""");
        int est = sc.nextInt();
        sc.nextLine();
        Estudiante participante = null;
        if (est == 1) {
            System.out.println("Matricula: ");
            String matricula = sc.nextLine().strip();
            //OPTIMIZAR (RECORRE TODO EL ARREGLO AUNQUE YA ENCONTRO EL ESTUDIANTE)
            for (Estudiante e : pa.getEstudiantes()) {
                if (matricula.equals(e.getMatricula())) {
                    participante = e;
                }
            }
            if (participante == null) {
                System.out.println("Participante no encontrado, Por favor vuelva a intentar.");
                return;
            }
        } else if (est == 2) {
            Random rd = new Random();
            int escogido = rd.nextInt(0, pa.getEstudiantes().length);
            participante = pa.getEstudiantes()[escogido];
        }

        System.out.println("Participante " + participante.getNombre() + " escogido.");
        System.out.println("");

        System.out.println("Por favor seleccione al apoyo: ");
        System.out.println("""
                           1. Ingresar matricula
                           2. Escoger Aleatorio""");
        int apo = sc.nextInt();
        Estudiante apoyo = null;
        if (apo == 1) {
            System.out.println("Matricula: ");
            //sc.nextLine();
            String matricula = sc.nextLine().strip();
            for (Estudiante e : pa.getEstudiantes()) {
                if (matricula.equals(e.getMatricula())) {
                    apoyo = e;
                }
            }
            if (apoyo == null) {
                System.out.println("Apoyo no encontrado, Por favor vuelva a intentar.");
                return;
            }
        } else if (apo == 2) {
            Random rd = new Random();
            int escogido = rd.nextInt(0, pa.getEstudiantes().length);
            apoyo = pa.getEstudiantes()[escogido];
        }
        System.out.println("Apoyo " + apoyo.getNombre() + " escogido");
        System.out.println("");

        Juego juego = new Juego(ma, pa, participante, apoyo, "hoy", 1, Configuracion.cuestionarios.get(0));
        juego.iniciarJuego();
    }

    public static void Reporte() {
        

    }
}
