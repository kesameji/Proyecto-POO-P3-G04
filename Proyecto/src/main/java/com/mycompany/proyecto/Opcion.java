package com.mycompany.proyecto;


public class Opcion {
    private String texto;
    private Respuesta respuesta;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }
    
    public Opcion(String texto, Respuesta respuesta){
        this.texto = texto;
        this.respuesta = respuesta;
    }
}
