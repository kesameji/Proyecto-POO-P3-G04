package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class Juego {

    //atributos privados
    final private TerminoAcademico termino;
    final private Materia materia;
    final private Paralelo paralelo;
    final private Estudiante participante;
    final private Estudiante apoyo;
    final private String fecha;
    private int nivelActual;
    private double tiempo;
    private Cuestionario ListaPreguntas;
    private int preguntaActual;
    private int preguntasContestadas;
    private String premio;

    private ArrayList<String> opciones = new ArrayList<String>();

    //constructor con todos los atributos, inicializa el arreglo opciones que sera usado en el juego
    public Juego(TerminoAcademico termino, Materia materia, Paralelo paralelo, Estudiante participante, Estudiante apoyo, String fecha, int nivelActual, Cuestionario ListaPreguntas) {
        this.termino = termino;
        this.materia = materia;
        this.paralelo = paralelo;
        this.participante = participante;
        this.apoyo = apoyo;
        this.fecha = fecha;
        this.nivelActual = nivelActual;
        tiempo = 0;
        this.ListaPreguntas = ListaPreguntas;
        opciones = new ArrayList<String>();
        opciones.add("A");
        opciones.add("B");
        opciones.add("C");
        opciones.add("D");
    }

    //Getter de Paralelo
    public Paralelo getParalelo() {
        return paralelo;
    }

    /**
     * Inicia un nuevo juego
     */
    public void iniciarJuego() {
        //variables a usar durante el jeugo
        Scanner sc = new Scanner(System.in);
        boolean resultado = true;
        nivelActual = 0;
        preguntaActual = 0;
        System.out.println("REGLAS");
        System.out.println("Por favor responder con: A , B, C, D");
        System.out.println("Solo se puede usar un comodin por turno, si se trata de usar dos en el mismo turno perderas.");

        //recorre cada pregunta en la lista
        for (Pregunta pregunta : ListaPreguntas.getPreguntas()) {
            nivelActual = pregunta.getNivel();
            mostrarPregunta(pregunta);
            System.out.println("Respuesta: ");
            String respuesta = sc.nextLine().strip().toUpperCase();

            //en caso que el usuario quiera usar un comodin
            if (respuesta.equals("*")) {
                usarComodin(pregunta);
                System.out.println("");
                mostrarPregunta(pregunta);
                System.out.println("Respuesta: ");
                respuesta = sc.nextLine().strip();
                //en caso que el participante use dos comodines en el mismo turno
                if (respuesta.equals("*")) {
                    System.out.println("Solo se puede usar un comodin por turno");
                    terminarJuego(false, Configuracion.juegos);
                    return;
                }
            }
            //llama a la funcion verificarRespuesta y guarda el resultado
            resultado = verificarRespuesta(respuesta, pregunta);

            //en caso que la respuesta fuera incorrecta termina el juego
            if (resultado == false) {
                System.out.println("Respuesta Incorrecta");
                terminarJuego(resultado, Configuracion.juegos);
                System.out.println("");
                return;
            }

            //le dice al participante que la respuesta fue correcta y aumenta la pregunta acutal
            System.out.println("Respuesta Correcta");
            System.out.println("");
            preguntaActual++;
        }

        // termina el juego usando el resultado de la ultima pregunta
        terminarJuego(resultado, Configuracion.juegos);
        System.out.println("");
    }

    /**
     * Metodo que se encarga de la administracion y uso de los comodines
     *
     * @param pregunta Pregunta donde se van a usar los comodines
     */
    public void usarComodin(Pregunta pregunta) {
        //muestra una lista de los comodines disponibles
        ArrayList<Integer> disponibles = mostrarComodines();
        //en caso de que no hallan mas comodines disponibles
        if (disponibles == null) {
            return;
        }

        Scanner sc = new Scanner(System.in);
        //obtiene el indice del comodin seleccionado
        int comodinSelecto = opciones.indexOf(sc.nextLine().strip());

        //Verifica que el comodin seleccionado este disponible
        if (disponibles.contains(comodinSelecto)) {
            Comodin comodin = ListaPreguntas.getComodines()[comodinSelecto];
            comodin.setUso(true);
            //llama a un metedo distinto dependiendo del nombre
            switch (comodin.getNombre()) {
                case "50/50":
                    comodin.usarCincuenta(pregunta);
                    break;
                case "Llamada al apoyo":
                    comodin.usarApoyo(apoyo, pregunta);
                    break;
                case "Pregunta al publico":
                    comodin.usarPublico(paralelo.getEstudiantes().size(), pregunta);
                    break;
            }
        } else {
            System.out.println("El comodin que selecciono ya no se encuentra disponible");
        }
    }

    /**
     * Muestra los comodines que se encuentran disponibles Devuevle una lita de
     * los comodines disponibles
     *
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> mostrarComodines() {
        System.out.println("");
        System.out.println("Comodines disponibles");
        ArrayList<Integer> disponibles = new ArrayList<Integer>();

        //recorre las lista de comodines buscando los que tengan su uso en false
        for (int i = 0; i < ListaPreguntas.getComodines().length; i++) {
            Comodin comodin = ListaPreguntas.getComodines()[i];
            if (comodin.getUso() == false) {
                System.out.println(opciones.get(i) + ". " + comodin.getNombre());
                disponibles.add(i);
            }
        }
        //en caso de que no hallan mas comodines disponibles
        if (disponibles.isEmpty()) {
            System.out.println("Ya no quedan Comodines");
            return null;
        }
        return disponibles;
    }

    /**
     * Metodo que verifica si la respuesta ingresada por el usuario es correcta o no.
     * @param respuesta String que contiene la respuesta del participante
     * @param pregunta pregunta donde se verificara la respuesta
     * @return booleano con valor de true si la respuesta en correcta y false si la respuesta es incorrecta
     */
    public boolean verificarRespuesta(String respuesta, Pregunta pregunta) {
        //solo se puede usar un comodin por turno
        if (respuesta.equals("*")) {
            System.out.println("El comodin ya no esta disponible");
        }
        //optiene el indice de la respuesta
        int opcion = opciones.indexOf(respuesta);
        //verifica las respuesta
        if (pregunta.getOpciones()[opcion].getRespuesta() == Respuesta.CORRECTO && opcion >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Evalua el resultado del juego e imprime un mensaje dependiendo del mismo.
     *
     * @param resultado Boolean-Representa si el juego se termina con una
     * derrota o victoria
     * @param juegos lista de juegos realizados
     */
    public void terminarJuego(boolean resultado, ArrayList<Juego> juegos) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Juego Terminado");
        //en caso de victoria pide el premio
        if (resultado) {
            System.out.println("GANASTE!!!!!");
            System.out.println("Por favor ingresar el premio: ");
            premio = sc.nextLine().strip();
        } // en caso de derrota
        else {
            System.out.println("Perdiste :c ");
            System.out.println("");
        }
        generarReporte(juegos);
    }

    /**
     * Recibe una pregunta e imprime por pantalla el enunciado y cada una de sus
     * opciones.
     *
     * @param pregunta Pregunta a mostrar
     */
    public void mostrarPregunta(Pregunta pregunta) {
        System.out.println(pregunta.getEnunciado());
        for (int i = 0; i < pregunta.getOpciones().length; i++) {
            System.out.println(opciones.get(i) + ". " + pregunta.getOpciones()[i].getTexto());
        }
    }

    /**
     * Genera un reporte al finalizar el juego con informacion relevante del juego
     * @param juegos lista de los juegos realizados
     */
    public void generarReporte(ArrayList<Juego> juegos) {
        System.out.println("-----------------------");
        System.out.println("REPORTE DEL JUEGO");
        System.out.println("Fecha del juego: " + fecha);
        System.out.println("Participante: " + participante);
        System.out.println("Nivel máximo alcanzado: " + nivelActual);
        //System.out.println("Tiempo: " + tiempo);
        System.out.println("Cantidad de preguntas contestadas: " + preguntaActual);
        System.out.println("Comodines utilizados: ");
        for (Comodin co : ListaPreguntas.getComodines()) {
            if (co.getUso() == true) {
                System.out.println(co.getNombre());
            }
        }
        if (premio != null) {
            System.out.println("Feliciades se ha ganado: " + premio);
        }
        //aniade el juego a la lista de juegos realizados
        juegos.add(this);
    }

    //Sobrecarga del metodo toString que retorna una cadena de texto con informacion del juego
    @Override
    public String toString() {
        return "Fecha del juego: " + fecha
                + "; \nParticipante: " + participante
                + "; \nNivel máximo alcanzado: " + nivelActual
                + //"; Tiempo: " + tiempo + 
                "; \nCantidad de preguntas contestadas: " + preguntaActual
                + "; \nComodines utilizados: "
                + "\nPremio: " + premio;
    }

}
