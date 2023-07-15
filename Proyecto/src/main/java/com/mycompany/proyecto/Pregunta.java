package com.mycompany.proyecto;
import java.util.Scanner;
import java.util.ArrayList;

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
                System.out.println(p.getOpciones()[i]);
            }    
        }
    }
    
    //método que permite agregar la pregunta
    public void agregarPregunta(ArrayList<Pregunta> preguntas){
        Scanner sc = new Scanner(System.in);
        //Se muestran las materias para escoger aquella donde se agregará la pregunta
        mostrarMaterias();
        int posMateria;
        do{
            System.out.println("Escoja la materia de la lista de materias");
            posMateria = sc.nextInt();
            if(posMateria<1 || posMateria>materias.size()){
                System.out.println("ESTA MATERIA NO ESTÁ REGISTRADA");
            }
    
        }while(posMateria<1 || posMateria>materias.size());

        System.out.println("<<Accediendo a agregar pregunta>>");
        mostrarPreguntas();
        System.out.println("Ingrese enunciado para su pregunta: ");
        String enunciadoIngr1 = sc.nextLine();
        System.out.println("Ingrese el nivel de dificultada para su pregunta": );
        int nivelIngr = sc.nextInt();
        String textoOpcion1;
        Respuesta respOpcion1;
        System.out.println("Ingrese el texto para una opcion ");
        textoOpcion1 = sc.nextLine();
        System.out.println("Ingrese el tipo de respuesta con el siguiente formato: Respuesta.CORRECTO o Respuesta.INCORRECTO: ");
        respOpcion1 = sc.nextLine();
        String enunciadoIngr2;
        System.out.println("Ingrese enunciado para su pregunta: ");
        enunciadoIngr2 = sc.nextLine();
        //Falta completar
            
        
        
        
        
        
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
