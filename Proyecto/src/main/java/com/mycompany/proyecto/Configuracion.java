package com.mycompany.proyecto;

import java.util.ArrayList;
import java.util.Scanner;

public class Configuracion {

    static ArrayList<TerminoAcademico> terminos = new ArrayList<TerminoAcademico>();

    static ArrayList<Materia> materias = new ArrayList<Materia>();

    static ArrayList<Paralelo> paralelos = new ArrayList<Paralelo>();

    static ArrayList<Cuestionario> cuestionarios = new ArrayList<Cuestionario>();
    
    static ArrayList<Juego> juegos = new ArrayList<>();

    public static void ingresarTerminos(TerminoAcademico termino) {
        terminos.add(termino);
    }
    
    
    public static void ingresarMateria(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("INGRESO DE MATERIA");
        System.out.print("Ingrese el código de la materia: ");
        String codigoIngresado = sc.nextLine();
        System.out.print("Ingrese el nombre de la materia: ");
        String nombreIngresado = sc.nextLine();
        System.out.print("Ingrese la cantidad de niveles para las preguntas: ");
        int numNivelesIngresado = sc.nextInt();
        
        //sc.close();
        
        
        materias.add(new Materia(codigoIngresado,nombreIngresado,numNivelesIngresado));
        System.out.println("Materia ingresada correctamente\n");
        
    }
    
    public static void mostrarMaterias(){
        System.out.println("MATERIAS");
        for (Materia m: materias){
            System.out.println(m);
        }
    }
    
    public static void editarMateria(){
        Scanner sc = new Scanner(System.in);
        
        int opcion = 0;
        while (opcion != 3){
            do {
                System.out.println("MODIFICACIÓN DE MATERIA");
                System.out.println("1. Modificar por código de la materia");
                System.out.println("2. Modificar por nombre de la materia");
                System.out.println("3. Regresar");
                System.out.println("Ingrese una opcion: ");
                opcion = sc.nextInt();
                sc.nextLine();
                if (opcion < 1 || opcion > 3){
                    System.out.println("ERROR: Opcion incorrecta");
                }
            } while (opcion < 1 || opcion > 3);
            int i = 0;
            int posicion = -1;
            if (opcion == 1){
                System.out.print("Ingrese el código de la materia a editar: ");
                String codigoIngresado = sc.nextLine();
                for (Materia m: materias){
                    if (m.getCodigo().equals(codigoIngresado)){
                        posicion = i;
                    }
                    i++;
                }
            } else if (opcion == 2){
                System.out.print("Ingrese el código de la materia a editar: ");
                String nombreIngresado = sc.nextLine();
                for (Materia m: materias){
                    if (m.getNombre().equals(nombreIngresado)){
                        posicion = i;
                    }
                    i++;
                }
            }
            if (posicion != -1 && (opcion == 1 || opcion == 2)){
                int opcion2;
                do{
                    System.out.println("1. Modificar nombre de la materia");
                    System.out.println("2. Modificar cantidad de niveles de la materia");
                    opcion2 = sc.nextInt();
                    if (opcion2 != 1 && opcion2 != 2){
                        System.out.println("ERROR: Opcion incorrecta");
                    }
                }while(opcion2 != 1 && opcion2 != 2);
                if (opcion2 == 1){
                    System.out.println("Ingrese nuevo nombre de la materia: ");
                    String nombreNuevo = sc.nextLine();
                    materias.get(posicion).setNombre(nombreNuevo);
                    
                } else if (opcion2 == 2){
                    System.out.println("Ingrese nueva cantidad de niveles de la materia: ");
                    int numNivelesNuevo = sc.nextInt();
                    materias.get(posicion).setNumeroNiveles(numNivelesNuevo);
                }
            }
        }
        
        //sc.close();
        
    }

    public static void ingresarParalelo(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("INGRESO DE PARALELO");
        int i = 1;
        //muestra una lista de todas las materias
        for (Materia m: materias){
            System.out.println(i + ". " + m);
            i++;
        }
        //ingreso y validación de materia
        int posicionMateria;
        do {
            System.out.print("Seleccione la materia: ");
            posicionMateria = sc.nextInt();
            if (posicionMateria < 1 || posicionMateria > materias.size()){
                System.out.println("ERROR: Posición no existente");
            }
        }while (posicionMateria < 1 || posicionMateria > materias.size());
        int j = 1;
        //muestra una lista de todos los términos académicos de la materia seleccionada
        for (TerminoAcademico t: materias.get(posicionMateria - 1).getTerminos()){
            System.out.println("\t" + j + ". " + t);
        }
        //ingreso y validación de término académico
        int posicionTerminoAcademico;
        do{
            System.out.print("Seleccione el término académico: ");
            posicionTerminoAcademico = sc.nextInt();
            if (posicionTerminoAcademico < 1 || posicionTerminoAcademico > materias.get(posicionMateria - 1).getTerminos().size()){
                System.out.println("ERROR: Posición no existente");
            }
        }while (posicionTerminoAcademico < 1 || posicionTerminoAcademico > materias.get(posicionMateria - 1).getTerminos().size());
        
        //ingreso y agregación de número de paralelo
        System.out.print("Ingrese el número de paralelo: ");
        int numParaleloIngresado = sc.nextInt();
        
        paralelos.add(posicionTerminoAcademico - 1,new Paralelo(numParaleloIngresado,materias.get(posicionMateria - 1).getTerminos().get(posicionTerminoAcademico - 1),materias.get(posicionMateria - 1)));
        
        
        System.out.println("Se ha añadido correctamento el paralelo");
        
        //sc.close();
    }
    
    public static void mostrarParalelos(){
        System.out.println("PARALELOS");
        int i = 1;
        for (Paralelo p: paralelos){
            System.out.println(i + ". " + p);
        }
    }
    
    //método que permite eliminar un paralelo
    public static void eliminarParalelo(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("ELIMINACIÓN DE PARALELO");
        
        //muestra una lista de todos los paralelos
        int i = 1;
        for (Paralelo p: paralelos){
            System.out.println(i + ". " + p);
        }
        //ingreso y validación de paralelo
        int posicionParalelo;
        do{
            System.out.print("Seleccione un paralelo a eliminar: ");
            posicionParalelo = sc.nextInt();
            if (posicionParalelo < 0 || posicionParalelo > paralelos.size()){
                System.out.println("ERROR: Posición no existente");
            }
        }while (posicionParalelo < 0 || posicionParalelo > paralelos.size());
        
        //elimina el paralelo seleccionado
        paralelos.remove(posicionParalelo - 1);
        
        //sc.close();
    }

    public static void ingresarCuestionario(Cuestionario cuestionario) {
        cuestionarios.add(cuestionario);
    }

    public static void CargarInformacion() {
        Estudiante[] estudiantes = new Estudiante[6];
        estudiantes[0] = new Estudiante("202210712", "BORBOR GUTIERREZ VICTOR DANIEL", "vicbguti@espol.edu.ec");
        estudiantes[1] = new Estudiante("202208880", "MEJIA PARRA KEVIN SANTIAGO", "kesameji@espol.edu.ec");
        estudiantes[2] = new Estudiante("202211355", "ARAUJO ORTEGA DIEGO ENZO JAVIER", "dienarau@espol.edu.ec");
        estudiantes[3] = new Estudiante("202006086", "CABRERA VIVANCO ALVARO DAVID", "alvdcabr@espol.edu.ec");
        estudiantes[4] = new Estudiante("202216164", "CHINO VILLAMAR ISAIAS DANIEL", "danissan@espol.edu.ec");
        estudiantes[5] = new Estudiante("202211421 ", "BRIONES OLEAS STEVEN BRAYAN", "bsbrione@espol.edu.ec");

        TerminoAcademico ta = new TerminoAcademico("2023", 1);
        Materia ma = new Materia("CCPG1052", "POO", 3);
        Paralelo pa = new Paralelo(3, estudiantes, ta, ma);

        terminos.add(ta);
        materias.add(ma);
        paralelos.add(pa);

        ArrayList<Pregunta> pre = new ArrayList<Pregunta>();
        
        pre.add(new Pregunta("Con que palabra se crea una clase", 1, new Opcion[]{
            new Opcion("Class", Respuesta.INCORRECTO),
            new Opcion("Clase", Respuesta.INCORRECTO),
            new Opcion("class", Respuesta.CORRECTO),
            new Opcion("clas", Respuesta.INCORRECTO)}));
        
        pre.add(new Pregunta("Que es POO", 1, new Opcion[]{
            new Opcion("Programacion orientada a objetos", Respuesta.CORRECTO),
            new Opcion("Problemas orientados a osos", Respuesta.INCORRECTO),
            new Opcion("Paradigma oriental de objetos", Respuesta.INCORRECTO),
            new Opcion("Presentacion occidental de ornitorincos", Respuesta.INCORRECTO)}));
        
        pre.add(new Pregunta("Qué línea de código se utiliza para importar ArrayList?", 1, new Opcion[]{
            new Opcion("import java.util.Stack", Respuesta.INCORRECTO),
            new Opcion("package java.util.Scanner", Respuesta.INCORRECTO),
            new Opcion("import java.util.ArrayList", Respuesta.CORRECTO),
            new Opcion("package java.util.ArrayList", Respuesta.INCORRECTO)}));
        
        pre.add(new Pregunta("Como se llama el compilador de java", 2, new Opcion[]{
            new Opcion("Java", Respuesta.INCORRECTO),
            new Opcion("JavaC", Respuesta.CORRECTO),
            new Opcion("JVM", Respuesta.INCORRECTO),
            new Opcion(".Jar", Respuesta.INCORRECTO)}));
        
        pre.add(new Pregunta("Que elementos corresponden a la firma de un metodo", 2, new Opcion[]{
            new Opcion("Tipo de retorno, nombre, parametros", Respuesta.INCORRECTO),
            new Opcion("modificador de acceso, tipo de retorno, nombre", Respuesta.INCORRECTO),
            new Opcion("modificador de acceso, nombre", Respuesta.INCORRECTO),
            new Opcion("nombre, parametros", Respuesta.CORRECTO)}));
        
        pre.add(new Pregunta("Cuáles son los modificadores de acceso?", 2, new Opcion[]{
            new Opcion("public, protected, private y default", Respuesta.CORRECTO),
            new Opcion("public, protected, private y friend", Respuesta.INCORRECTO),
            new Opcion("public, protected, private e internal", Respuesta.INCORRECTO),
            new Opcion("public, protected, private y  package-private plus", Respuesta.INCORRECTO)}));
        
        pre.add(new Pregunta("Cual es el valor por defecto de un arreglo de caracteres", 3, new Opcion[]{
            new Opcion("\\u000", Respuesta.CORRECTO),
            new Opcion("null", Respuesta.INCORRECTO),
            new Opcion("\\null", Respuesta.INCORRECTO),
            new Opcion("''", Respuesta.INCORRECTO)}));
        
        pre.add(new Pregunta("Cual es el tipo de retorno del metodo hashCode", 3, new Opcion[]{
            new Opcion("Object", Respuesta.INCORRECTO),
            new Opcion("boolean", Respuesta.INCORRECTO),
            new Opcion("int", Respuesta.CORRECTO),
            new Opcion("HashMap", Respuesta.INCORRECTO)}));
        
        pre.add(new Pregunta("Qué palabra reservada se utiliza para llamar en una clase hija a un atributo o método de una clase padre?", 3, new Opcion[]{
            new Opcion("father", Respuesta.INCORRECTO),
            new Opcion("super", Respuesta.CORRECTO),
            new Opcion("parent", Respuesta.INCORRECTO),
            new Opcion("this", Respuesta.INCORRECTO)}));

        cuestionarios.add(new Cuestionario(
                new Comodin[]{new Comodin("50/50"), new Comodin("Llamada al apoyo"), new Comodin("Pregunta al publico")},
                pre));

    }
}
