package com.mycompany.proyecto;

import java.util.ArrayList;

public class TerminoAcademico {
    //atributos privados 
    private String anio;
    private int numeroTermino;
    private ArrayList<Materia> materias = new ArrayList<Materia>();

    //constructor con todos los parámetros
    public TerminoAcademico(String anio, int numeroTermino) {
        this.anio = anio;
        this.numeroTermino = numeroTermino;
    }

    //Getters y setters respectivos
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

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void addMaterias(Materia materia) {
        materias.add(materia);
    }

    //Sobrecarga del metodo toString retorna el anio y el numero del termino
    @Override
    public String toString() {
        return anio + "-" + numeroTermino;
    }
}
