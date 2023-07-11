package com.mycompany.proyecto;


public class Paralelo {
    private String numeroParalelo;
    private Estudiante[] estudiantes;
    private TerminoAcademico termino;
    private Materia materia;

    //constructor con todos los parámetros
    public Paralelo(String numeroParalelo, Estudiante[] estudiantes, TerminoAcademico termino, Materia materia) {
        this.numeroParalelo = numeroParalelo;
        this.estudiantes = estudiantes;
        this.termino = termino;
        this.materia = materia;
    }

    
    public String getNumeroParalelo() {
        return numeroParalelo;
    }

    public void setNumeroParalelo(String numeroParalelo) {
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
    
    //método que permite editar el paralelo
    public void editarParalelo(){
        
    }
}
