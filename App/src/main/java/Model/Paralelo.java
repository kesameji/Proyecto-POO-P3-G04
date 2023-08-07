package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Paralelo implements Serializable{
    private int numeroParalelo;
    private ArrayList<Estudiante> estudiantes;

    //constructor que inicializa todos los parametros menos a los estudiantes.
    public Paralelo(int numeroParalelo) {
        this.numeroParalelo = numeroParalelo;

    }

    //Sobrecarga del constructos, usa el constructor anterior e incializa a los estudiantes.
    public Paralelo(int numeroParalelo, TerminoAcademico termino, Materia materia, ArrayList<Estudiante> estudiantes) {
        this(numeroParalelo);
        this.estudiantes = estudiantes;
    }
    
    //Getters y setters respectivos de los atributos
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
    
    public void AgregarEstuadiante(Estudiante e){
        this.estudiantes.add(e);
    }

    //Sobrecarga del mentodo toString retorna el numero del paralelo, la materia a la que pertenece y su terminoAcademico
    @Override
    public String toString(){
        return "Paralelo " + numeroParalelo;
    }
    
    
    
    
    
}
