package com.mycompany.proyecto;

import java.util.ArrayList;

public class Materia {
    private String codigo;
    private String nombre;
    private int numeroNiveles;
    private ArrayList<TerminoAcademico> terminos;

    public Materia(String codigo, String nombre, int numeroNiveles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.numeroNiveles = numeroNiveles;
        terminos = new ArrayList<>();
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

    public ArrayList<TerminoAcademico> getTerminos() {
        return terminos;
    }

    public void setTermino(ArrayList<TerminoAcademico> terminos) {
        this.terminos = terminos;
    }
    

    @Override
    public String toString(){
        return "codigo: " + codigo + 
                "; materia: " + nombre;
    }
    
}
