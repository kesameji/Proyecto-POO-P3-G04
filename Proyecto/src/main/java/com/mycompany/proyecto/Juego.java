package com.mycompany.proyecto;


public class Juego {
    private Materia materia;
    private Paralelo paralelo;
    private Estudiante participante;
    private Estudiante apoyo;
    private String fecha;
    private int nivelActual;
    private double tiempo;
    private Cuestionario ListaPreguntas;

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
    
    
    
    public void iniciarJuego(){
        
    }
    
    public void usarComodin(){
        
    }
    
    public void responderPregunta(){
        
    }
    
    public void verificarRespuesta(){
        
    }
    
    public void terminarJuego(){
        
    }
}
