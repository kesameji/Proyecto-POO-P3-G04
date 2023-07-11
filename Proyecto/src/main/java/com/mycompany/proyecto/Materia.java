package com.mycompany.proyecto;

public class Materia {
    private String codigo;
    private String nombre;
    private int numeroNiveles;
    private TerminoAcademico termino;

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
    
    
}
