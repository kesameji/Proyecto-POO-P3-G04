package com.mycompany.proyecto;

import java.util.ArrayList;

public class Materia {
    //atributos privados 
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

    //Constructor de la clase Materia, inicializa todos los atributos menos las preguntas
    public Materia(String codigo, String nombre, int numeroNiveles, TerminoAcademico termino) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.numeroNiveles = numeroNiveles;
        this.termino = termino;
    }
    
    //Sobrecarga del Constructor de la clase Materia, utiliza el contructor anterior pero este, inicializa las preguntas
    public Materia(String codigo, String nombre, int numeroNiveles, TerminoAcademico termino, ArrayList<Pregunta> preguntas) {
        this(codigo, nombre, numeroNiveles, termino);
        this.preguntas = preguntas;
    }
    
    
    // Getters y setters de los atributos, Codigo, Nombre, NumeroNiveles, Termino, Preguntas
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
    

    //Sobreescritura del metodo toString
    @Override
    public String toString(){
        return "codigo: " + codigo + 
                "; materia: " + nombre;
    }
    
}
