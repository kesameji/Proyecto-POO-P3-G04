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
    private int preguntasContestadas;
    private String premio;
    
    private ArrayList<String> opciones = new ArrayList<String>();

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
        opciones = new ArrayList<String>();
        opciones.add("A");
        opciones.add("B");
        opciones.add("C");
        opciones.add("D");
    }

    public void iniciarJuego() {
        Scanner sc = new Scanner(System.in);
        boolean resultado = true;
        nivelActual=0;
        preguntaActual = 0;
        
        for (Pregunta pregunta : ListaPreguntas.getPreguntas()) {
            nivelActual = pregunta.getNivel();
            mostrarPregunta(pregunta);
            System.out.println("Respuesta: ");
            String respuesta = sc.nextLine().strip();
            
            if (respuesta.equals("*")) {
                usarComodin(pregunta);
                System.out.println("");
                mostrarPregunta(pregunta);
                System.out.println("Respuesta: ");
                respuesta = sc.nextLine().strip();
                
                if (respuesta.equals("*")) {
                    System.out.println("pero tu eres bobo?");
                }
                resultado = false;
            }
            
            resultado = verificarRespuesta(respuesta, pregunta);
            
            if (resultado == false) {
                System.out.println("Respuesta Incorrecta");
                terminarJuego(resultado, Configuracion.juegos);
                System.out.println("");
                return;
            }
            
            System.out.println("Respuesta Correcta");
            System.out.println("");
            preguntaActual++;
        }
        terminarJuego(resultado, Configuracion.juegos);
        System.out.println("");
    }

    public void usarComodin(Pregunta pregunta) {

        ArrayList<Integer> disponibles = mostrarComodines();
        Scanner sc = new Scanner(System.in);
        int comodinSelecto = opciones.indexOf(sc.nextLine().strip());

        if (disponibles == null) {
            return;
        }

        if (disponibles.contains(comodinSelecto)) {
            Comodin comodin = ListaPreguntas.getComodines()[comodinSelecto];
            comodin.setUso(true);
            switch (comodin.getNombre()) {
                case "50/50":
                    comodin.usarCincuenta(pregunta);
                    break;
                case "Llamada al apoyo":
                    comodin.usarApoyo(apoyo, pregunta);
                    break;
                case "Pregunta al publico":
                    comodin.usarPublico(paralelo.getEstudiantes().length, pregunta);
                    break;
            }
        } else {
            System.out.println("El comodin que selecciono ya no se encuentra disponible");
        }

    }

    public ArrayList<Integer> mostrarComodines() {
        System.out.println("");
        System.out.println("Comodines disponibles");
        ArrayList<Integer> disponibles = new ArrayList<Integer>();

        for (int i = 0; i < ListaPreguntas.getComodines().length; i++) {
            Comodin comodin = ListaPreguntas.getComodines()[i];
            if (comodin.getUso() == false) {
                System.out.println(opciones.get(i) + ". " + comodin.getNombre());
                disponibles.add(i);
            }
        }

        if (disponibles.isEmpty()) {
            System.out.println("Ya no quedan Comodines");
            return null;
        }
        return disponibles;
    }

    public void responderPregunta() {

    }

    public boolean verificarRespuesta(String respuesta, Pregunta pregunta) {
        if (respuesta.equals("*")) {
            System.out.println("El comodin ya no esta disponible");
        }
        int opcion = opciones.indexOf(respuesta);
        if (pregunta.getOpciones()[opcion].getRespuesta() == Respuesta.CORRECTO && opcion >= 0) {
            return true;
        }
        return false;
    }

    public void terminarJuego(boolean resultado, ArrayList<Juego> juegos) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Juego Terminado");
        if (resultado) {
            System.out.println("GANASTE!!!!!");
            System.out.println("Por favor ingresar el premio: ");
            premio = sc.nextLine().strip();
        } else {
            System.out.println("Perdiste :c ");
        }
        generarReporte(juegos);
    }

    public void mostrarPregunta(Pregunta pregunta) {
        System.out.println(pregunta.getEnunciado());
        for (int i = 0; i < pregunta.getOpciones().length; i++) {
            System.out.println(opciones.get(i) + ". " + pregunta.getOpciones()[i].getTexto());
        }
    }

    public void generarReporte(ArrayList<Juego> juegos) {
        System.out.println("-----------------------");
        System.out.println("REPORTE DEL JUEGO");
        System.out.println("Fecha del juego: " + fecha);
        System.out.println("Participante: " + participante);
        System.out.println("Nivel máximo alcanzado: " + nivelActual);
        //System.out.println("Tiempo: " + tiempo);
        System.out.println("Cantidad de preguntas contestadas: " + preguntaActual);
        System.out.println("Comodines utilizados: ");
        for (Comodin co : ListaPreguntas.getComodines()){
            if (co.getUso() == true) System.out.println(co.getNombre());
        }
        System.out.println("Feliciades se ha ganado: " + premio);
        juegos.add(this);
    }

}
