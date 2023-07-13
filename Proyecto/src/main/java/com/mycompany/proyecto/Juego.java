package com.mycompany.proyecto;

import java.util.ArrayList;
import java.util.Scanner;

public class Juego {

    private Materia materia;
    private Paralelo paralelo;
    private Estudiante participante;
    private Estudiante apoyo;
    private String fecha;
    private int nivelActual;
    private double tiempo;
    private Cuestionario ListaPreguntas;
    private int preguntaActual;

    //constructor
    public Juego(Materia materia, Paralelo paralelo, Estudiante participante, Estudiante apoyo, String fecha, int nivelActual, Cuestionario ListaPreguntas) {
        this.materia = materia;
        this.paralelo = paralelo;
        this.participante = participante;
        this.apoyo = apoyo;
        this.fecha = fecha;
        this.nivelActual = nivelActual;
        tiempo = 0;
        this.ListaPreguntas = ListaPreguntas;
    }

    public void iniciarJuego() {
        Scanner sc = new Scanner(System.in);
        boolean resultado = true;
        for (Pregunta pregunta : ListaPreguntas.getPreguntas()) {
            mostrarPregunta(pregunta);
            System.out.println("Respuesta: ");
            String respuesta = sc.nextLine().strip();
            if (respuesta.equals("*")) {
                usarComodin(pregunta);
                System.out.println("");
                mostrarPregunta(pregunta);
                System.out.println("Respuesta: ");
                respuesta = sc.nextLine().strip();
                if (respuesta.equals("*")) System.out.println("pero tu eres bobo?");
                resultado = false;
            }
            resultado = verificarRespuesta(respuesta, pregunta);
            if (resultado == false) {
                System.out.println("Respuesta Incorrecta");
                terminarJuego(resultado);
                System.out.println("");
                return;
            }
            System.out.println("Respuesta Correcta");
        }
        terminarJuego(resultado);
        System.out.println("");
    }

    public void usarComodin(Pregunta pregunta) {
        System.out.println("");
        System.out.println("Comodines disponibles");
        ArrayList<Integer> disponibles = new ArrayList<Integer>();

        for (int i = 0; i < ListaPreguntas.getComodines().length; i++) {
            Comodin comodin = ListaPreguntas.getComodines()[i];
            if (comodin.getUso() == false) {
                System.out.println((i + 1) + ". " + comodin.getNombre());
                disponibles.add((i + 1));
            }
        }
        
        if (disponibles.isEmpty()){
            System.out.println("Ya no quedan Comodines");
            return;
        }

        Scanner sc = new Scanner(System.in);
        int comodinSelecto = Integer.parseInt(sc.nextLine().strip());
        
        if (disponibles.contains(comodinSelecto)) {
            ListaPreguntas.getComodines()[comodinSelecto - 1].setUso(true);
            System.out.println("Ha seleccionado el comodin: "+comodinSelecto);
        }
        else {
            System.out.println("El comodin que selecciono ya no se encuentra disponible");
        }
        
    }

    public void responderPregunta() {

    }

    public boolean verificarRespuesta(String respuesta, Pregunta pregunta) {
        if (respuesta.equals("*")){
            System.out.println("El comodin ya no esta disponible");
        }
        int opcion = Integer.parseInt(respuesta) - 1;
        if (pregunta.getOpciones()[opcion].getRespuesta() == Respuesta.CORRECTO) {
            return true;
        }
        return false;
    }

    public void terminarJuego(boolean resultado) {
        System.out.println("Juego Terminado");
        if (resultado) {
            System.out.println("GANASTE!!!!!");
            System.out.println("-----------------------");
            System.out.println("Resumen del juego");
            System.out.println("Trabajo pendiente xd");
        }
        else {
            System.out.println("Perdiste :c ");
            System.out.println("-----------------------");
            System.out.println("Resumen del juego");
            System.out.println("Trabajo pendiente xd");
        }
    }

    public void mostrarPregunta(Pregunta pregunta) {
        System.out.println(pregunta.getEnunciado());
        for (int i = 0; i < pregunta.getOpciones().length; i++) {
            System.out.println((i + 1) + ". " + pregunta.getOpciones()[i].getTexto());
        }
    }
}
