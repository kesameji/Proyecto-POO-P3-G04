package com.mycompany.proyecto;
import java.util.Scanner;
import java.util.ArrayList();

public class Pregunta {
    private String enunciado;
    private int nivel;
    private Opcion[] opciones;
    
    //constructor con todos los parámetros
    public Pregunta(String enunciado, int nivel, Opcion[] opciones) {
        this.enunciado = enunciado;
        this.nivel = nivel;
        this.opciones = opciones;
    }
    

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Opcion[] getOpciones() {
        return opciones;
    }

    public void setOpciones(Opcion[] opciones) {
        this.opciones = opciones;
    }

    ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>(); 
    
    //método que muestra las preguntas
    public void mostrarPreguntas(ArrayList<Pregunta> preguntas){
        for(Pregunta p:preguntas){
            System.out.println(p.getEnunciado()+"\nNivel"+p.getNivel());
            for(int i=0; i<p.getOpciones().length; i++){
                System.out.println(p.getOpciones()[i];
            }    
        }
    }
    
    //método que permite editar la pregunta
    public void editarPregunta(ArrayList<Pregunta> preguntas){
        Scanner sc = new Scanner(System.in);
        int opcion=0;
        do{
            System.out.println("<<Accediendo a editar pregunta>>");
            System.out.println("1. Modificar el enunciado de la pregunta");
            System.out.println("2. Modificar el nivel de la pregunta");
            System.out.println("3. Modificar las opciones de la pregunta");
            System.out.println("4. Regresar");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextInt();
            if(opcion<0 || opcion>4){
                System.out.println("Opción no válida");
            }
        } //Codido imcompleto
        
    }
    
    //método que perimte eliminar una pregunta
    public void eliminarPregunta(){
        Scanner sc = new Scanner(System.in);
        System.out.println("<<Accediendo a eliminar pregunta>>");
        //Se muestra la lista de preguntas 
        mostrarPreguntas();
        int posPregunta;
        do{
            System.out.println("Ingrese el número de pregunta a eliminar: ");
            posPregunta= sc.nextInt();
            if(posPregunta<0 || posPregunta>preguntas.size()){
                System.out.println("Esa pregunta no existe en la lista");
            }
            
        }while(posPregunta<0 || posPregunta>preguntas.size());
        preguntas.remove(posPregunta-1);
    }
    
    
}
