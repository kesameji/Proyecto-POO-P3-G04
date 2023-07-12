package com.mycompany.proyecto;


import java.util.ArrayList;
import java.util.Scanner;

public class Paralelo {
    private int numeroParalelo;
    private Estudiante[] estudiantes;
    private TerminoAcademico termino;
    private Materia materia;

    public Paralelo(int numeroParalelo, TerminoAcademico termino, Materia materia) {
        this.numeroParalelo = numeroParalelo;
        this.termino = termino;
        this.materia = materia;
    }

    //constructor con todos los parámetros
    public Paralelo(int numeroParalelo, Estudiante[] estudiantes, TerminoAcademico termino, Materia materia) {
        this(numeroParalelo,termino,materia);
        this.estudiantes = estudiantes;
    }

    
    public int getNumeroParalelo() {
        return numeroParalelo;
    }

    public void setNumeroParalelo(int numeroParalelo) {
        this.numeroParalelo = numeroParalelo;
    }

    public Estudiante[] getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Estudiante[] estudiantes) {
        this.estudiantes = estudiantes;
    }

    public TerminoAcademico getTermino() {
        return termino;
    }

    public void setTermino(TerminoAcademico termino) {
        this.termino = termino;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    
    @Override
    public String toString(){
        return materia + "Termino: " + termino + "Paralelo: " + numeroParalelo;
    }
    
    //método que permite agregar un paralelo a un término académico de una materia
    public void agregarParalelo(ArrayList<Materia> materias, ArrayList<Paralelo> paralelos){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("INGRESO DE PARALELO");
        int i = 1;
        //muestra una lista de todas las materias
        for (Materia m: materias){
            System.out.println(i + ". " + m);
            i++;
        }
        //ingreso y validación de materia
        int posicionMateria;
        do {
            System.out.print("Seleccione la materia: ");
            posicionMateria = sc.nextInt();
            if (posicionMateria < 1 || posicionMateria > materias.size()){
                System.out.println("ERROR: Posición no existente");
            }
        }while (posicionMateria < 1 || posicionMateria > materias.size());
        int j = 1;
        //muestra una lista de todos los términos académicos de la materia seleccionada
        for (TerminoAcademico t: materias.get(posicionMateria - 1).getTerminos()){
            System.out.println("\t" + j + ". " + t);
        }
        //ingreso y validación de término académico
        int posicionTerminoAcademico;
        do{
            System.out.print("Seleccione el término académico: ");
            posicionTerminoAcademico = sc.nextInt();
            if (posicionTerminoAcademico < 1 || posicionTerminoAcademico > materias.get(posicionMateria - 1).getTerminos().size()){
                System.out.println("ERROR: Posición no existente");
            }
        }while (posicionTerminoAcademico < 1 || posicionTerminoAcademico > materias.get(posicionMateria - 1).getTerminos().size());
        
        //ingreso y agregación de número de paralelo
        System.out.print("Ingrese el número de paralelo: ");
        int numParaleloIngresado = sc.nextInt();
        
        paralelos.add(posicionTerminoAcademico - 1,new Paralelo(numParaleloIngresado,materias.get(posicionMateria - 1).getTerminos().get(posicionTerminoAcademico - 1),materias.get(posicionMateria - 1)));
        
        
        System.out.println("Se ha añadido correctamento el paralelo");
        
        sc.close();
    }
    
    //método que permite eliminar un paralelo
    public void eliminarParalelo(ArrayList<Paralelo> paralelos){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("ELIMINACIÓN DE PARALELO");
        
        //muestra una lista de todos los paralelos
        int i = 1;
        for (Paralelo p: paralelos){
            System.out.println(i + ". " + p);
        }
        //ingreso y validación de paralelo
        int posicionParalelo;
        do{
            System.out.print("Seleccione un paralelo a eliminar: ");
            posicionParalelo = sc.nextInt();
            if (posicionParalelo < 0 || posicionParalelo > paralelos.size()){
                System.out.println("ERROR: Posición no existente");
            }
        }while (posicionParalelo < 0 || posicionParalelo > paralelos.size());
        
        //elimina el paralelo seleccionado
        paralelos.remove(posicionParalelo - 1);
        
        sc.close();
    }
}
