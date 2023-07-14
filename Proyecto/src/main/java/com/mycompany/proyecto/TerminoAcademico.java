package com.mycompany.proyecto;
import java.util.Scanner();

public class TerminoAcademico {
    private String anio;
    private int numeroTermino;

    //constructor con todos los parámetros
    public TerminoAcademico(String anio, int numeroTermino) {
        this.anio = anio;
        this.numeroTermino = numeroTermino;
    }
    

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public int getNumeroTermino() {
        return numeroTermino;
    }

    public void setNumeroTermino(int numeroTermino) {
        this.numeroTermino = numeroTermino;
    }
    
    //método que solicita año y termino y lo añade al juego 
    public void ingresarTermino(){
        Scanner sc = new Scanner(System.in);
        String anioAct = "2023";
        String anioIngr;
        System.out.println("Ingrese el año del término académico");
        anioIngr = sc.nextLine();
        int convAnioAct = Integer.parseInt(anioAct);
        int convAnioIngr = Integer.parseInt(anioIngr);
        while(convAnioIngr<convAnioAct){
            System.out.println("Ingrese el año del término académico");
            anioIngr = sc.nextLine();
        }
        System.out.println("Ingrese el número del término académico");
        int numTerm = sc.nextInt();
        sc.close();

        terminos.add(new TerminoAcademico(anioIngr,numTerm));
        
    }
    
    //método que permite editar el año o número de término
    public void editarTermino(){
        
    }
    
    @Override
    public String toString(){
        return anio + "-" + numeroTermino;
    }
}
