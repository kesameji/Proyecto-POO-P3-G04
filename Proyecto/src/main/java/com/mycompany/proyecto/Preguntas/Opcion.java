package com.mycompany.proyecto.Preguntas;


public class Opcion {
    //atributos privados
    private String texto;
    private Respuesta respuesta;
    
    //constructor con todos los parámetros
    public Opcion(String texto, Respuesta respuesta){
        this.texto = texto;
        this.respuesta = respuesta;
    }
    
    //Getters y setters de todos los atributos
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
    
    //Sobreescritura del metodo toString
    @Override
    public String toString(){
        return texto;
    }
    
    
}
