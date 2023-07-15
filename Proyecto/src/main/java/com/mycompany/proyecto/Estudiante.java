package com.mycompany.proyecto;


public class Estudiante {
    
    private String matricula;
    private String nombre;
    private String correo;
    
    public Estudiante(){};
    
    //constructor con todos los parámetros
    public Estudiante(String matricula, String nombre, String correo) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.correo = correo;
    }

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
    
    @Override
    public String toString(){
        return "Nombre: " + nombre + "; Correo: " + correo;
    }
    
}
