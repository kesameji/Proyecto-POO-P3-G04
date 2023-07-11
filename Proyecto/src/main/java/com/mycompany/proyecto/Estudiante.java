package com.mycompany.proyecto;


public class Estudiante {
    
    private String matricula;
    private String correo;
    
    //constructor con todos los parámetros
    public Estudiante(String matricula, String correo) {
        this.matricula = matricula;
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
    
    
}
