package com.mycompany.proyecto;

import java.util.Random;

public class Comodin {

    private String nombre;
    private boolean uso;

    //constructor que inicializa el nombre y pone el uso en false
    public Comodin(String nombre) {
        this.nombre = nombre;
        uso = false;
    }

    //método que permite modificar el uso del comodin
    public void modificarUso() {

    }

    public Pregunta usarCincuenta(Pregunta pre) {
        Random rd = new Random();
        int eliminados = 0;
        
        
        while (eliminados < 2){
            int i = rd.nextInt(4);
            if (pre.getOpciones()[i].getRespuesta() == Respuesta.INCORRECTO){
                pre.getOpciones()[i].setTexto("");
                eliminados++;
            } 
        }
        return pre;
    }

    public void usarApoyo(Estudiante apoyo, Pregunta pre) {
        System.out.println("Llamando a " + apoyo.getNombre());
        int[] probabilidad = {1, 1, 0, 1, 1, 1, 1, 0, 1, 1};
        Random rd = new Random();
        int i = rd.nextInt(0, 10);

        boolean resultado = (probabilidad[i] == 1);
        for (Opcion op : pre.getOpciones()) {
            if (resultado && op.getRespuesta() == Respuesta.CORRECTO) {
                System.out.println(
                        apoyo.getNombre()
                        + " opina que la respuesta correcta es: "
                        + op.getTexto());
                return;
            }
        }
        System.out.println(
                apoyo.getNombre()
                + " opina que la respuesta correcta es:"
                + pre.getOpciones()[rd.nextInt(4)].getTexto());
    }

    public void usarPublico() {
        System.out.println("Pregunta al publico");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getUso() {
        return uso;
    }

    public void setUso(boolean uso) {
        this.uso = uso;
    }

}
