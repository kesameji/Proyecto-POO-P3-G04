package com.mycompany.proyecto.Preguntas;


public class Pregunta {
    //atributos privados 
    private String enunciado;
    private int nivel;
    private Opcion[] opciones;
    
    //constructor con todos los parámetros
    public Pregunta(String enunciado, int nivel, Opcion[] opciones) {
        this.enunciado = enunciado;
        this.nivel = nivel;
        this.opciones = opciones;
    }
    
    //Getters y setters de los atributos, Enunciado, Nivel, Opciones
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
    
    //Sobreescritura del metodo toString
    @Override
    public String toString(){
        return "Nivel " + nivel + " Pregunta: "+ enunciado;
    }
    
}
