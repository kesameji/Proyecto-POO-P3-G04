package com.mycompany.proyecto;

import java.util.Comparator;

public class OrdenPregunta implements Comparator<Pregunta> {

    //Implementacion del metodo Compare de la interfaz Comparator
    //Se usara para ordenar un arreglo de preguntas segun el nivel de cada pregunta
    //Para que aparescan las mas faciles primero
    @Override
    public int compare(Pregunta o1, Pregunta o2) {
        if (o1.getNivel() == o2.getNivel()) {
            return 0;
        }
        return (o1.getNivel() > o2.getNivel()) ? 1 : -1;
    }
}
