package com.mycompany.proyecto;


public class TerminoAcademico {
    private String anio;
    private int numeroTermino;

    //constructor con todos los parámetros
    public TerminoAcademico(String anio, int numeroTermino) {
        this.anio = anio;
        this.numeroTermino = numeroTermino;
    }
    

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public int getNumeroTermino() {
        return numeroTermino;
    }

    public void setNumeroTermino(int numeroTermino) {
        this.numeroTermino = numeroTermino;
    }
    
    //método que solicita año y termino y lo añade al juego 
    public void ingresarTermino(){
        
    }
    
    //método que permite editar el año o número de término
    public void editarTermino(){
        
    }
    
    @Override
    public String toString(){
        return anio + "-" + numeroTermino;
    }
}
