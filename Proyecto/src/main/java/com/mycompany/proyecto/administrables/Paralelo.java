package com.mycompany.proyecto.administrables;

public class Paralelo {
    private int numeroParalelo;
    private TerminoAcademico termino;
    private Materia materia;
    private Estudiante[] estudiantes;

    //constructor que inicializa todos los parametros menos a los estudiantes.
    public Paralelo(int numeroParalelo, TerminoAcademico termino, Materia materia) {
        this.numeroParalelo = numeroParalelo;
        this.termino = termino;
        this.materia = materia;
    }

    //Sobrecarga del constructos, usa el constructor anterior e incializa a los estudiantes.
    public Paralelo(int numeroParalelo, TerminoAcademico termino, Materia materia, Estudiante[] estudiantes) {
        this(numeroParalelo,termino,materia);
        this.estudiantes = estudiantes;
    }
    
    //Getters y setters respectivos de los atributos
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
    
    //Sobrecarga del mentodo toString retorna el numero del paralelo, la materia a la que pertenece y su terminoAcademico
    @Override
    public String toString(){
        return "Paralelo " + numeroParalelo + "; " + materia + "; Termino: " + termino;
    }
    
    
    
    
    
}
