package com.mycompany.proyecto;
import java.util.ArrayList;
import java.util.Scanner;

public class TerminoAcademico {
    private String anio;
    private int numeroTermino;
    private ArrayList<Materia> materias = new ArrayList<Materia>();

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

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void addMaterias(Materia materia) {
        materias.add(materia);
    }
    
    @Override
    public String toString(){
        return anio + "-" + numeroTermino;
    }
}
