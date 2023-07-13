package com.mycompany.proyecto;


import java.util.ArrayList;
import java.util.Scanner;

public class Paralelo {
    private int numeroParalelo;
    private Estudiante[] estudiantes;
    private TerminoAcademico termino;
    private Materia materia;

    public Paralelo(int numeroParalelo, TerminoAcademico termino, Materia materia) {
        this.numeroParalelo = numeroParalelo;
        this.termino = termino;
        this.materia = materia;
    }

    //constructor con todos los parámetros
    public Paralelo(int numeroParalelo, Estudiante[] estudiantes, TerminoAcademico termino, Materia materia) {
        this(numeroParalelo,termino,materia);
        this.estudiantes = estudiantes;
    }

    
    public int getNumeroParalelo() {
        return numeroParalelo;
    }

    public void setNumeroParalelo(int numeroParalelo) {
        this.numeroParalelo = numeroParalelo;
    }

    public Estudiante[] getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Estudiante[] estudiantes) {
        this.estudiantes = estudiantes;
    }

    public TerminoAcademico getTermino() {
        return termino;
    }

    public void setTermino(TerminoAcademico termino) {
        this.termino = termino;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    
    @Override
    public String toString(){
        return materia + "; Termino: " + termino + "; Paralelo: " + numeroParalelo;
    }
    
    
    
    
    
}
