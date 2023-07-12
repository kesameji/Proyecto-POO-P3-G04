package com.mycompany.proyecto;

import java.util.ArrayList;

public class Configuracion {

    static ArrayList<TerminoAcademico> terminos;

    static ArrayList<Materia> materias;

    static ArrayList<Paralelo> paralelos;

    static ArrayList<Cuestionario> cuestionarios;

    public static void ingresarTerminos(TerminoAcademico termino) {
        terminos.add(termino);
    }

    public static void ingresarMateria(Materia materia) {
        materias.add(materia);
    }

    public static void ingresarParalelo(Paralelo paralelo) {
        paralelos.add(paralelo);
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
        Materia ma = new Materia("123", "POO", 3, ta);
        Paralelo pa = new Paralelo("7", estudiantes, ta, ma);

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

        cuestionarios.add(new Cuestionario(
                new Comodin[]{new Comodin("50/50"), new Comodin(""), new Comodin("")},
                pre));

    }
}
