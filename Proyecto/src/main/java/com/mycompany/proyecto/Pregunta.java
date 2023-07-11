package com.mycompany.proyecto;

public class Pregunta {
    private String enunciado;
    private int nivel;
    private Opcion[] opciones;
    
    //constructor con todos los parámetros
    public Pregunta(String enunciado, int nivel, Opcion[] opciones) {
        this.enunciado = enunciado;
        this.nivel = nivel;
        this.opciones = opciones;
    }
    

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Opcion[] getOpciones() {
        return opciones;
    }

    public void setOpciones(Opcion[] opciones) {
        this.opciones = opciones;
    }
    
    //método que muestra la pregunta
    public void mostrarPregunta(){
        
    }
    
    //método que permite editar la pregunta
    public void editarPregunta(){
        
    }
    
    //método que perimte eliminar una pregunta
    public void eliminarPregunta(){
        
    }
    
    
}
