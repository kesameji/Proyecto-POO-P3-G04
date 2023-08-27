package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Paralelo implements Serializable{
    private Materia materia;
    private TerminoAcademico termino;
    private String pathEstudiantes;
    private int numeroParalelo;
    private ArrayList<Estudiante> estudiantes;

    //constructor que inicializa todos los parametros menos a los estudiantes.
    public Paralelo(int numeroParalelo, TerminoAcademico termino,Materia materia ) {
        this.numeroParalelo = numeroParalelo;
        this.termino = termino;
        this.materia = materia;

    }

    //Sobrecarga del constructos, usa el constructor anterior e incializa a los estudiantes.
    public Paralelo(int numeroParalelo, TerminoAcademico termino, Materia materia, ArrayList<Estudiante> estudiantes) {
        this(numeroParalelo, termino, materia);
        this.estudiantes = estudiantes;
    }
    
    //Getters y setters respectivos de los atributos

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public TerminoAcademico getTermino() {
        return termino;
    }

    public void setTermino(TerminoAcademico termino) {
        this.termino = termino;
    }
    
    public int getNumeroParalelo() {
        return numeroParalelo;
    }

    public void setNumeroParalelo(int numeroParalelo) {
        this.numeroParalelo = numeroParalelo;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public String getPathEstudiantes() {
        return pathEstudiantes;
    }

    public void setPathEstudiantes(String pathEstudiantes) {
        this.pathEstudiantes = pathEstudiantes;
    }
    
    public void AgregarEstuadiante(Estudiante e){
        this.estudiantes.add(e);
    }
    public void AgregarEstuadiante(ArrayList<Estudiante> e){
        this.estudiantes.addAll(e);
    }
    
    public Estudiante obtenerEstudiante(String matricula){
        for (Estudiante e : estudiantes ){
            if (e.getMatricula().equals(matricula))return e;
        }
        return null;
    }
    
    //Sobrecarga del mentodo toString retorna el numero del paralelo, la materia a la que pertenece y su terminoAcademico
    @Override
    public String toString(){
        return "Paralelo " + numeroParalelo;
    }
}