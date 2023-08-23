package Model;

import java.io.Serializable;
import java.util.Random;

public class Comodin implements Serializable{

    //Atributos privados
    //private static final long serialVersionUID = 6170483963309263638L;
    private String nombre;
    private boolean uso; // controla que el comodin solo tenga un uso
    private int nivel;// controla que el comodin solo tenga un uso

    //constructor que inicializa el nombre y pone el uso en false
    public Comodin(String nombre) {
        this.nombre = nombre;
        uso = false;
    }

    /**
     * Devuelve un objeto pregunta con dos de sus opciones modificadas para que su texto sea una cadena vacia.
     * El argumento pregunta debe tener como minimo 2 opciones incorrectas.
     * @param pre pregunta a modificar.
     * @return Pregunta
    */
    public Pregunta usarCincuenta(Pregunta pre) {
        //genera numeros aleatorios 
        Random rd = new Random();
        int eliminados = 0;
        //bucle que se repite hasta que 2 opcines hallan sido modificadas.
        while (eliminados < 2){
            int i = rd.nextInt(4);
            //si la opcion es incorrecta modifica su texto
            if (pre.getOpciones()[i].getRespuesta() == Respuesta.INCORRECTO){
                pre.getOpciones()[i].setTexto("");
                eliminados++;
            } 
        }
        return pre;
    }

    /**
     * Imprime un mensaje que puede contener las respuesta correcta o no de una pregunta
     * @param apoyo Estudiante que fue escogido como el apoyo
     * @param pre 
     */
    public void usarApoyo(Estudiante apoyo, Pregunta pre) {
        System.out.println("Llamando a " + apoyo.getNombre());
        //probabilidad de que el apoyo brinde la respuesta correcta 80%
        int[] probabilidad = {1, 1, 0, 1, 1, 1, 1, 0, 1, 1};
        Random rd = new Random();
        int i = rd.nextInt(10);
        boolean resultado = (probabilidad[i] == 1);
        //si salio un uno devuelve la respuesta correcta
        for (Opcion op : pre.getOpciones()) {
            if (resultado && op.getRespuesta() == Respuesta.CORRECTO) {
                System.out.println(
                        apoyo.getNombre()
                        + " opina que la respuesta correcta es: "
                        + op.getTexto());
                return;
            }
        }
        //Si salio 0 se le da al apoyo una ultima oportunidad de acertar
        System.out.println(
                apoyo.getNombre()
                + " opina que la respuesta correcta es:"
                + pre.getOpciones()[rd.nextInt(4)].getTexto());
    }

    /**
     * Imprime un mensaje con los porcentajes que opina el publico debe ser la respuesta correcta.
     * @param totalAlumnos Cantidad de alumnos en el paralelo
     * @param pre Pregunta de donde se van a sacar las opciones
     */
    public void usarPublico(int totalAlumnos, Pregunta pre) {
        System.out.println("Encuestando al publico");
        //probabilidad de que un alumno escoja la respuesta correcta
        //esta probabilidad de suma al 25% de acertar que tienen todos los alumnos
        int[] probabilidad = {0, 1, 0, 0, 0, 1, 0, 0, 1, 0};
        Random rd = new Random();
        int[] porcentajes = new int[4];
        
        int correcta = 0;
        for (int i = 0; i < pre.getOpciones().length; i++){
            if (pre.getOpciones()[i].getRespuesta() == Respuesta.CORRECTO)
                correcta = i;
        }
        
        for (int i = 0; i < totalAlumnos-1; i++){
            int j = rd.nextInt(10);
            if (probabilidad[j] == 1){
                porcentajes[correcta]++;
            }
            else {
                porcentajes[rd.nextInt(4)]++;
            }
        }
        //imprime los resultados que cada opcion
        System.out.println("Resultados de las votaciones: ");
        System.out.println();
        System.out.println("B: "+((porcentajes[1]*100)/totalAlumnos)+"%");
        System.out.println("C: "+((porcentajes[2]*100)/totalAlumnos)+"%");
        System.out.println("D: "+((porcentajes[3]*100)/totalAlumnos)+"%");
        
        
    }
    //Getters y Setters de los atributos Nombre y uso
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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Comodin{" + "nombre=" + nombre + ", uso=" + uso + '}';
    }
    
}
