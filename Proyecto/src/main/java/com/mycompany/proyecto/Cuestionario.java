package com.mycompany.proyecto;

import java.util.ArrayList;

public class Cuestionario {
    private Comodin comodines[];
    private ArrayList<Pregunta> preguntas;

    //constructor con todos los parámetros
    public Cuestionario(Comodin[] comodines, ArrayList<Pregunta> preguntas) {
        this.comodines = comodines;
        this.preguntas = preguntas;
    }

    //getters & setters
    public Comodin[] getComodines() {
        return comodines;
    }

    public void setComodines(Comodin[] comodines) {
        this.comodines = comodines;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
    
    
}
