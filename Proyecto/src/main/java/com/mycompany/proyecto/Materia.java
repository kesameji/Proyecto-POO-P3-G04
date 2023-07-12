package com.mycompany.proyecto;

import java.util.Scanner;
import java.util.ArrayList;

public class Materia {
    private String codigo;
    private String nombre;
    private int numeroNiveles;
    private ArrayList<TerminoAcademico> terminos;

    public Materia(String codigo, String nombre, int numeroNiveles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.numeroNiveles = numeroNiveles;
        terminos = new ArrayList<>();
    }
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroNiveles() {
        return numeroNiveles;
    }

    public void setNumeroNiveles(int numeroNiveles) {
        this.numeroNiveles = numeroNiveles;
    }

    public ArrayList<TerminoAcademico> getTerminos() {
        return terminos;
    }

    public void setTermino(ArrayList<TerminoAcademico> terminos) {
        this.terminos = terminos;
    }
    
    //método que permite ingresar una materia
    public void ingresarMateria(ArrayList<Materia> materias){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("INGRESO DE MATERIA");
        System.out.print("Ingrese el código de la materia: ");
        String codigoIngresado = sc.nextLine();
        System.out.print("\nIngrese el código de la materia: ");
        String nombreIngresado = sc.nextLine();
        System.out.print("\nIngrese la cantidad de niveles para las preguntas: ");
        int numNivelesIngresado = sc.nextInt();
        
        sc.close();
        
        materias.add(new Materia(codigoIngresado,nombreIngresado,numNivelesIngresado));
    }
    
    //método que permite editar una materia
    public void editarMateria(ArrayList<Materia> materias){
        Scanner sc = new Scanner(System.in);
        
        int opcion = 0;
        while (opcion != 3){
            do {
                System.out.println("MODIFICACIÓN DE MATERIA");
                System.out.println("1. Modificar por código de la materia");
                System.out.println("2. Modificar por nombre de la materia");
                System.out.println("3. Regresar");
                System.out.println("Ingrese una opcion: ");
                opcion = sc.nextInt();
                if (opcion < 1 || opcion > 3){
                    System.out.println("ERROR: Opcion incorrecta");
                }
            } while (opcion < 1 || opcion > 3);
            int i = 0;
            int posicion = -1;
            if (opcion == 1){
                System.out.print("Ingrese el código de la materia a editar: ");
                String codigoIngresado = sc.nextLine();
                for (Materia m: materias){
                    if (m.getCodigo().equals(codigoIngresado)){
                        posicion = i;
                    }
                    i++;
                }
            } else if (opcion == 2){
                System.out.print("Ingrese el código de la materia a editar: ");
                String nombreIngresado = sc.nextLine();
                for (Materia m: materias){
                    if (m.getNombre().equals(nombreIngresado)){
                        posicion = i;
                    }
                    i++;
                }
            }
            if (posicion != -1 && (opcion == 1 || opcion == 2)){
                int opcion2;
                do{
                    System.out.println("1. Modificar nombre de la materia");
                    System.out.println("2. Modificar cantidad de niveles de la materia");
                    opcion2 = sc.nextInt();
                    if (opcion2 != 1 && opcion2 != 2){
                        System.out.println("ERROR: Opcion incorrecta");
                    }
                }while(opcion2 != 1 && opcion2 != 2);
                if (opcion2 == 1){
                    System.out.println("Ingrese nuevo nombre de la materia: ");
                    String nombreNuevo = sc.nextLine();
                    materias.get(posicion).setNombre(nombreNuevo);
                    
                } else if (opcion2 == 2){
                    System.out.println("Ingrese nueva cantidad de niveles de la materia: ");
                    int numNivelesNuevo = sc.nextInt();
                    materias.get(posicion).setNumeroNiveles(numNivelesNuevo);
                }
            }
        }
        
        sc.close();
        
    }
    
    @Override
    public String toString(){
        return "codigo: " + codigo + 
                "; materia: " + nombre;
    }
    
}
