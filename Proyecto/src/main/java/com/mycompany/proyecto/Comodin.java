package com.mycompany.proyecto;


public class Comodin {
    private String nombre;
    private boolean uso;
    

    //constructor que inicializa el nombre y pone el uso en false
    public Comodin(String nombre) {
        this.nombre = nombre;
        uso = false;
    }

    
    //método que permite modificar el uso del comodin
    public void modificarUso(){
        
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
