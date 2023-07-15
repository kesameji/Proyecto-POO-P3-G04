package com.mycompany.proyecto;

import java.util.ArrayList;

public class Materia {
    private String codigo;
    private String nombre;
    private int numeroNiveles;
    private TerminoAcademico termino;
    private ArrayList<Pregunta> preguntas;

    /*public Materia(String codigo, String nombre, int numeroNiveles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.numeroNiveles = numeroNiveles;
        terminos = new ArrayList<>();
    }*/

    public Materia(String codigo, String nombre, int numeroNiveles, TerminoAcademico termino) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.numeroNiveles = numeroNiveles;
        this.termino = termino;
    }
    
    public Materia(String codigo, String nombre, int numeroNiveles, TerminoAcademico termino, ArrayList<Pregunta> preguntas) {
        this(codigo, nombre, numeroNiveles, termino);
        this.preguntas = preguntas;
    }
    
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroNiveles() {
        return numeroNiveles;
    }

    public void setNumeroNiveles(int numeroNiveles) {
        this.numeroNiveles = numeroNiveles;
    }

    public TerminoAcademico getTermino() {
        return termino;
    }

    public void setTermino(TerminoAcademico termino) {
        this.termino = termino;
    }
    
    public ArrayList<Pregunta> getPreguntas(){
        return preguntas;
    }
    

    @Override
    public String toString(){
        return "codigo: " + codigo + 
                "; materia: " + nombre;
    }
    
}
