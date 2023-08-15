package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class TerminoAcademico implements Serializable {
    //atributos privados 
    private String anio;
    private int numeroTermino;
    private ArrayList<Materia> materias = new ArrayList<Materia>();

    //constructor con todos los parámetros
    public TerminoAcademico(String anio, int numeroTermino) {
        this.anio = anio;
        this.numeroTermino = numeroTermino;
    }

    //Getters y setters respectivos
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
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TerminoAcademico other = (TerminoAcademico) obj;
        if (this.numeroTermino != other.numeroTermino) {
            return false;
        }
        if (!Objects.equals(this.anio, other.anio)) {
            return false;
        }
        return Objects.equals(this.materias, other.materias);
    }

    
    //Sobrecarga del metodo toString retorna el anio y el numero del termino
    @Override
    public String toString() {
        return anio + "-" + numeroTermino;
    }
}
