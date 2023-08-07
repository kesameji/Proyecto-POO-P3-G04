package Model;
import java.io.Serializable;

public class Estudiante implements Serializable{

    //atributos privados
    private String matricula;
    private String nombre;
    private String correo;

    //contructor vacio
    public Estudiante() {
    }

    ;
    
    //constructor con todos los parámetros
    public Estudiante(String matricula, String nombre, String correo) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.correo = correo;
    }

    //Getters y Setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Sobrecarga del metodo toString retorna el nombre y el correo del alumno
    @Override
    public String toString() {
        return "Nombre: " + nombre + "; Correo: " + correo;
    }

}
