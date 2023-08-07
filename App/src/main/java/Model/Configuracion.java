package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

public class Configuracion {

    //Creacion e inicialización de listas para el programa
    static ArrayList<TerminoAcademico> terminos = new ArrayList<TerminoAcademico>();

    static ArrayList<Materia> materias = new ArrayList<Materia>();

    static ArrayList<Paralelo> paralelos = new ArrayList<Paralelo>();

    static ArrayList<Cuestionario> cuestionarios = new ArrayList<Cuestionario>();

    static ArrayList<Juego> juegos = new ArrayList<>();

    static TerminoAcademico terminoJuego;

    /*
    * Método estático que muestra una lista de todos los términos cargados 
    * en el programa.
     */
    public static void mostrarTerminos() {
        System.out.println("TÉRMINOS ACADÉMICOS");
        int i = 1;
        for (TerminoAcademico t : terminos) {
            System.out.println(i + ". " + t);
            i++;

        }
        System.out.println();
    }

    /*
    * Método estático que permite ingresar al usuario el código, nombre y 
    * cantidad de niveles de una materia al programa.
     */
    public static void ingresarMateria() {
        Scanner sc = new Scanner(System.in);

        //Ingreso del código de la materia
        String codigoIngresado;
        do {
            System.out.println("INGRESO DE MATERIA");
            System.out.print("Ingrese el código de la materia: ");
            codigoIngresado = sc.nextLine();
            if (encontrarMateria(codigoIngresado) != -1) {
                System.out.println("Código de materia ya existente\n");
            }
        } while (encontrarMateria(codigoIngresado) != -1); //Validación de que el código de la materia no este repetida

        //Ingreso del nombre de la materia
        System.out.print("Ingrese el nombre de la materia: ");
        String nombreIngresado = sc.nextLine();

        //Ingreso de la cantidad de niveles de la materia
        System.out.print("Ingrese la cantidad de niveles para las preguntas: ");
        int numNivelesIngresado = sc.nextInt();
        sc.nextLine();

        //Ingreso de los términos de la materia
        System.out.println("Ingrese el termino de la materia");
        int posicionTermino;
        mostrarTerminos();
        //Se verifica que existan en términos cargados en el programa
        if (terminos.isEmpty()) {
            System.out.println("Por favor Ingrese terminos antes de continuar");
            return;
        }

        do {
            System.out.print("Seleccione el termino: ");
            posicionTermino = Integer.parseInt(sc.nextLine()) - 1;
            if (posicionTermino < 0 || posicionTermino > (Configuracion.terminos.size() - 1)) {
                System.out.println("Opcion no encontrada\n");
            }
        } while (posicionTermino < 0 || posicionTermino > (Configuracion.terminos.size() - 1)); //Validación de que el término escogido exista
        TerminoAcademico ta = terminos.get(posicionTermino);

        //Agregación de la materia a la lista de materias cargado en el programa
        materias.add(new Materia(codigoIngresado, nombreIngresado, numNivelesIngresado));
        System.out.println("Materia ingresada correctamente\n");

    }


    /*
    * Método estático que permite ingresar al usuario el año y el número de un 
    * término acádemico .
     */
    public static void ingresarTermino() {
        Scanner sc = new Scanner(System.in);

        //Ingreso del año y número de un término académico
        String anio;
        int numero;
        do {
            System.out.println("INGRESO DE TERMINO ACADEMICO");
            do {
                System.out.print("Ingrese el anio del termino: ");
                anio = sc.nextLine();
                if (Integer.parseInt(anio) > 2023) {
                    System.out.println("Año incorrecto: año mayor al actual");
                }
            } while (Integer.parseInt(anio) > 2023); //Validación de que el año sea menor al actual
            System.out.print("Ingrese el numero de termino: ");
            numero = Integer.parseInt(sc.nextLine());
            if (encontrarTermino(anio, numero) != -1) {
                System.out.println("Termino Academico ya existente\n");
            }
        } while (encontrarTermino(anio, numero) != -1); //Validación de que el término académico no este repetido

        //Agregación del término académico a la lista de términos cargado en el programa
        terminos.add(new TerminoAcademico(anio, numero));
        System.out.println("Termino ingresado correctamente\n");
    }


    /*
    * Método estático que muestra una lista de todas las materias cargadas en 
    * el programa.
     */
    public static void mostrarMaterias() {
        System.out.println("MATERIAS");
        int i = 1;
        for (Materia m : materias) {
            System.out.println(i + ". " + m);
            i++;
        }
    }

    /*
    * Método estático que muestra una lista de las materias de un ArrayList de 
    * tipo Materia.
    * 
    * @param ma El parámetro ma es un ArrayList de tipo Materia
     */
    public static void mostrarMaterias(ArrayList<Materia> ma) {
        System.out.println("MATERIAS");
        int i = 1;
        for (Materia m : ma) {
            System.out.println(i + ". " + m);
            i++;
        }
    }

    /*
    * Método estático que permite editar al usuario el año o el número de un 
    * término académico.
     */
    public static void editarTermino() {
        Scanner sc = new Scanner(System.in);
        String opcion;
        System.out.println("MODIFICACIÓN DEL TERMINO ACADEMICO"
                + "Escoga una termino para modificar"
                + "1. Escoger el termino por el año y número"
                + "2. Mostrar una lista de los terminos y escoger uno                           "
                + "3. Regresar                           "
                + "Ingrese una opcion:");
        opcion = sc.nextLine().strip();
        int op = Integer.parseInt(opcion);
        System.out.println();
        int posicionTermino;

        if (op == 1 || op == 2) {
            String anio;
            int numero;

            if (op == 1) {
                // Ingreso del término a modificar
                System.out.print("Ingrese el año del termino: ");
                anio = sc.nextLine();
                System.out.print("Ingrese el numero de termino: ");
                numero = Integer.parseInt(sc.nextLine());
                System.out.println();

                posicionTermino = encontrarTermino(anio, numero);
            } else {
                // Verificación de que existan términos cargados en el programa
                if (terminos.isEmpty()) {
                    System.out.println("No existen terminos para editar");
                    return;
                }

                mostrarTerminos();

                // Selección de término académico
                do {
                    System.out.print("Seleccione el termino a editar: ");
                    posicionTermino = Integer.parseInt(sc.nextLine()) - 1;
                    if (posicionTermino < 0 || posicionTermino > (Configuracion.terminos.size() - 1)) {
                        System.out.println("Opcion no encontrada\n");
                    }
                } while (posicionTermino < 0 || posicionTermino > (Configuracion.terminos.size() - 1)); // Validación de que término el término académico exista
            }

            // Verificación de que el término escogido exista
            if (posicionTermino != -1) {
                TerminoAcademico ta = terminos.get(posicionTermino);

                // Ingreso del nuevo año de término académico
                do {
                    System.out.print("Ingrese el nuevo año del termino: ");
                    anio = sc.nextLine();
                    if (Integer.parseInt(anio) > 2023) {
                        System.out.println("Año incorrecto: año mayor al actual");
                    }
                } while (Integer.parseInt(anio) > 2023); //Validación de que el año sea menor al actual

                // Ingreso del nuevo número de término académico
                System.out.print("Ingrese el nuevo numero de termino: ");
                numero = Integer.parseInt(sc.nextLine());

                //Edición del término acádemico
                ta.setAnio(anio);
                ta.setNumeroTermino(numero);
            } else {
                System.out.println("Término no encontrado");
            }
            System.out.println();
        }
    }

    /*
    * Método estático que permite al usuario escoger un término académico
    * para la creación de un nuevo juego.
     */
    public static void escogerTermino() {
        // Validación de que existan términos académicos cargados en el programa
        if (terminos.isEmpty()) {
            System.out.println("No existen terminos para escoger");
            return;
        }

        Scanner sc = new Scanner(System.in);

        mostrarTerminos();

        // Selección de término académico para la creación de un nuevo juego
        int posicionTermino;
        do {
            System.out.print("Seleccione el termino a escoger: ");
            posicionTermino = Integer.parseInt(sc.nextLine()) - 1;
            if (posicionTermino < 0 || posicionTermino > (Configuracion.terminos.size() - 1)) {
                System.out.println("Opcion no encontrada\n");
            }
        } while (posicionTermino < 0 || posicionTermino > (Configuracion.terminos.size() - 1)); // Validación de que el término escogido exista

        // Guardamos el término académico escogido para la creación de un nuevo juego
        terminoJuego = terminos.get(posicionTermino);
        System.out.println("");
    }

    /*
    * Método estático que recibe el código de una materia y devuelve la posición
    * de una materia cargada en el programa que coincida con el código recibido.
    * Devuelve -1 si no se encuentra una materia.
    * 
    * @param materia El parámetro materia es un String del código de la materia
    * @return La posición de una materia cargada en el programa
     */
    public static int encontrarMateria(String materia) {
        int i = 0;
        for (Materia m : materias) {
            if (m.getCodigo().equals(materia)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /*
    * Método estático que recibe el año y número de un término académico y 
    * devuelve la posición de un término académico cargada en el programa que
    * coincida con el año y número recibido.
    * Devuelve -1 si no se encuentra un término académico.
    * 
    * @param anio El parámetro anio es un String del año del término académico
    * @param numero El parámetro numero es el número del término académico
    * @return La posición de una materia cargada en el programa
     */
    public static int encontrarTermino(String anio, int numero) {
        int i = 0;
        for (TerminoAcademico ta : terminos) {
            if (anio.equals(ta.getAnio()) && numero == ta.getNumeroTermino()) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /*
    * Método estático que permite al usuario escoger una materia y editar el 
    * nombre o la cantidad de niveles de la materia escogida.
     */
    public static void editarMateria() {
        Scanner sc = new Scanner(System.in);

        int opcion = 0;
        while (opcion != 3) { //
            System.out.println("MODIFICACIÓN DE MATERIA"
                    + "Escoga una materia para modificar"
                    + "1. Escoger por código de la materia"
                    + "2. Mostrar una lista de las materas y escoger una"
                    + "3. Regresar"
                    + "Ingrese una opcion:");
            opcion = sc.nextInt();
            System.out.println();

            // Elección de una materia cargada en el programa
            int posicionMateria;
            if (opcion == 1 || opcion == 2) {
                if (opcion == 1) {
                    //Elección de materia ingresando el código de la materia
                    sc.nextLine();
                    System.out.print("Ingrese el código de la materia a editar: ");
                    String codigoIngresado = sc.nextLine();
                    posicionMateria = encontrarMateria(codigoIngresado);
                } else {
                    Configuracion.mostrarMaterias();

                    //Elección de materia mediante una lista de todas las materias cargadas en el programa
                    do {
                        System.out.print("Seleccione la materia a editar: ");
                        posicionMateria = (sc.nextInt()) - 1;
                        if (posicionMateria < 0 || posicionMateria > (Configuracion.materias.size() - 1)) {
                            System.out.println("Opcion no encontrada\n");
                        }
                    } while (posicionMateria < 0 || posicionMateria > (Configuracion.materias.size() - 1)); // Validación de que la materia escogida exista
                }
                System.out.println();

                // Verificación de que la materia escogida exista en el programa
                if (posicionMateria != -1) {
                    int opcion2 = 0;
                    while (opcion2 != 3) {
                        System.out.println("Qué desea modificar de " + materias.get(posicionMateria) + "?");
                        System.out.println("1. Modificar nombre de la materia"
                                + "2. Modificar cantidad de niveles de la materia"
                                + "3. Regresar"
                                + "Ingrese una opcion:");
                        opcion2 = sc.nextInt();
                        System.out.println();

                        // Modificación de la materia escogida
                        if (opcion2 == 1 || opcion2 == 2) {
                            if (opcion2 == 1) {
                                // Modificación del nombre de la materia escogida
                                sc.nextLine();
                                System.out.print("Ingrese un nuevo nombre de la materia: ");
                                String nombreNuevo = sc.nextLine();
                                materias.get(posicionMateria).setNombre(nombreNuevo);
                            } else {
                                // Modificación de la cantidad de niveles de la materia escogida
                                System.out.print("Ingrese una nueva cantidad de niveles de la materia: ");
                                int numNivelesNuevo = sc.nextInt();
                                materias.get(posicionMateria).setNumeroNiveles(numNivelesNuevo);
                            }
                            System.out.println("Materia modificada correctamente\n");
                        } else if (opcion != 3) {
                            System.out.println("Opcion no encontrada\n");
                        }
                    }
                } else {
                    System.out.println("Materia no encontrada\n");
                }
            } else if (opcion != 3) {
                System.out.println("Opcion no encontrada\n");
            }
        }

    }

    /*
    * Método estático que permite al usuario ingresar el número de un paralelo 
    * a la materia y término académico escogido por el usuario.
    * Agrega tres estudiantes por defecto en el paralelo ingresado.
     */
 /*
    public static void ingresarParalelo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("INGRESO DE PARALELO");
        mostrarMaterias();
        
        //Elección de una materia
        int posicionMateria;
        do {
            System.out.print("Seleccione la materia: ");
            posicionMateria = (sc.nextInt()) - 1;
            if (posicionMateria < 0 || posicionMateria > (materias.size() - 1)) {
                System.out.println("Materia no existente\n");
            }
        } while (posicionMateria < 0 || posicionMateria > (materias.size() - 1)); // Validación de que la materia escogida exista
        System.out.println();
        
        mostrarTerminos();
        
        //Elección de un término académico
        int posicionTerminoAcademico;
        do {
            System.out.print("Seleccione el término académico: ");
            posicionTerminoAcademico = (sc.nextInt()) - 1;
            if (posicionTerminoAcademico < 0 || posicionTerminoAcademico > terminos.size() - 1) {
                System.out.println("Término académico no existente\n");
            }
        } while (posicionTerminoAcademico < 0 || posicionTerminoAcademico > terminos.size() - 1); // Validación de que el término académico escogido exista

        //Ingreso del número del nuevo paralelo
        int numParaleloIngresado;
        do {
            System.out.print("\nIngrese el número del nuevo paralelo: ");
            numParaleloIngresado = sc.nextInt();
            if (numParaleloIngresado < 1 || repiteParalelo(numParaleloIngresado, posicionMateria, posicionTerminoAcademico)) {
                System.out.println("Paralelo repetido");
            }
        } while (numParaleloIngresado < 1 || repiteParalelo(numParaleloIngresado, posicionMateria, posicionTerminoAcademico)); // Validación de que el paralelo ingresado no se repita o que le número del paralelo sea menor que 1

        // Agregación de 3 estudiantes por defecto en el paralelo ingresado
        Estudiante[] estudiantes = new Estudiante[3];
        estudiantes[0] = new Estudiante("202208880", "MEJIA PARRA KEVIN SANTIAGO", "kesameji@espol.edu.ec");
        estudiantes[1] = new Estudiante("202211355", "ARAUJO ORTEGA DIEGO ENZO JAVIER", "dienarau@espol.edu.ec");
        estudiantes[2] = new Estudiante("202006086", "CABRERA VIVANCO ALVARO DAVID", "alvdcabr@espol.edu.ec");

        System.out.println("Cargando archivo de estudiantes........");

        //Agregación del paralelo a una lista de todos los paralelos cargados en el programa
        //paralelos.add(new Paralelo(numParaleloIngresado, terminos.get(posicionTerminoAcademico), materias.get(posicionMateria), estudiantes));

        System.out.println("Se ha añadido correctamento el paralelo\n");

    }
    
    
    /*
    * Método estático que verifica un paralelo se repite.
    * Devuelve true si se repite y false si no se repite.
    *
    * @param numeroParalelo El parámetro numeroParalelo es el número de un paralelo
    * @param posicionMateria El parámetro posicionMateria es la posición de la materia en una lista de todas las materias cargadas en el programa
    * @param posicionTermino El parámetro posicionTermino es la posición del término académico en una lista de todos los términos académicos cargados en el programa
    * @return La existencia del paralelo
     */
 /*
    public static boolean repiteParalelo(int numeroParalelo, int posicionMateria, int posicionTermino) {
        for (Paralelo p : paralelos) {
            if (p.getNumeroParalelo() == numeroParalelo && p.getMateria().equals(materias.get(posicionMateria)) && p.getTermino().equals(terminos.get(posicionTermino))) {
                return true;
            }
        }
        return false;
    }

    
    /*
    * Método estático que verifica si existen paralelos de una materia recibida.
    * Devuelve true si existen paralelos y false si no hay paralelos.
    * 
    * @param posicionMateria El parámetro posicionMateria es la posición de la materia en una lista de todas las materias cargadas en el programa
    * @return La existencia de paralelos en la materia recibida
     */
 /*
    public static boolean existenParalelos(int posicionMateria) {
        for (Paralelo p : paralelos) {
            if (p.getMateria().equals(materias.get(posicionMateria))) {
                return true;
            }
        }
        return false;
    }

    
    /*
    * Método estático que verifica si existen paralelos de una materia y un 
    * término académico recibida.
    * Devuelve true si existen paralelos y false si no hay paralelos.
    * 
    * @param posicionMateria El parámetro posicionMateria es la posición de la materia en una lista de todas las materias cargadas en el programa
    * @param posicionTermino El parámetro posicionTermino es la posición del término académico en una lista de todos los términos académicos cargados en el programa
    * @return La existencia de paralelos en la materia y término académico recibido
     */
 /*
    public static boolean existenParalelos(int posicionMateria, int posicionTermino) {
        for (Paralelo p : paralelos) {
            if (p.getMateria().equals(materias.get(posicionMateria)) && p.getTermino().equals(terminos.get(posicionTermino))) {
                return true;
            }
        }
        return false;
    }

    
    /*
    * Método estático que muestra una lista de todas las materias y sus 
    * paralelos cargados en el programa.
     */
 /*
    public static void mostrarMateriasyParalelos() {
        System.out.println("MATERIAS");
        int i = 1;
        for (Materia m : materias) {
            System.out.println(i + ". " + m);
            i++;
            for (Paralelo p : paralelos) {
                if (p.getMateria().equals(m)) {
                    System.out.println("\t Paralelo " + p.getNumeroParalelo());
                }
            }
        }
    }

    
    /*
    * Método estático que muestra una lista de todos los paralelos cargados
    * en el programa.
     */
    public static void mostrarParalelos() {
        System.out.println("PARALELOS");
        int i = 1;
        for (Paralelo p : paralelos) {
            System.out.println(i + ". " + p);
            i++;
        }
    }

    /*
    * Método estático que muestra una lista de todos los paralelos de una 
    * materia recibida.
    * Devuelve el tamaño de la lista mostrada.
    *
    * @param posicionMateria El parámetro posicionMateria es la posición de la materia en una lista de todas las materias cargadas en el programa
    * @return El tamaño de la lista de todos los paralelos de la materia recibida
     */
 /*
    public static int mostrarParalelos(int posicionMateria) {
        System.out.println("PARALELOS");
        int i = 1;
        for (Paralelo p : paralelos) {
            if (p.getMateria().equals(materias.get(posicionMateria))) {
                System.out.println(i + ". Paralelo " + p.getNumeroParalelo());
                i++;
            }
        }
        return i - 1;
    }
    
    
    /*
    * Método estático que muestra una lista de todos los paralelos que sean de 
    * la materia y el término académico recibido.
    * Devuelve el tamaño de la lista mostrada.
    *
    * @param posicionMateria El parámetro posicionMateria es la posición de la materia en una lista de todas las materias cargadas en el programa
    * @param posicionTermino El parámetro posicionTermino es la posición del término académico en una lista de todos los términos académicos cargados en el programa
    * @return El tamaño de la lista de todos los paralelos que sean de la materia y el término académico recibida
     */
 /*
    public static int mostrarParalelos(int posicionMateria, int posicionTermino) {
        System.out.println("PARALELOS");
        int i = 1;
        for (Paralelo p : paralelos) {
            if (p.getTermino().equals(terminos.get(posicionTermino)) && p.getMateria().equals(materias.get(posicionMateria))) {
                System.out.println(i + ". " + p);
                i++;
            }
        }
        return i - 1;
    }

    
    /*
    * Método estático que permite al usuario escoger un paralelo y eliminarlo.
     */
    public static void eliminarParalelo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("ELIMINACIÓN DE PARALELO");

        mostrarParalelos();

        // Elección de paralelo a eliminar
        int posicionParalelo;
        do {
            System.out.print("Seleccione un paralelo a eliminar (0 para regresar): ");
            posicionParalelo = sc.nextInt() - 1;
            if (posicionParalelo < -1 || posicionParalelo > paralelos.size() - 1) {
                System.out.println("Paralelo no existente\n");
            }
        } while (posicionParalelo < -1 || posicionParalelo > paralelos.size() - 1); // Validación de que el paralelo escogido exista

        // Verificación de que el paralelo exista
        if (posicionParalelo != -1) {
            // Eliminación el paralelo seleccionado
            paralelos.remove(posicionParalelo);
            System.out.println("Paralelo eliminado correctamente");
        }
        System.out.println();

    }

    /*
    * Método estático que permite agregar un cuestionario a la lista 
    * cuestionarios.
     */
    public static void ingresarCuestionario(Cuestionario cuestionario) {
        cuestionarios.add(cuestionario);
    }

    /*
    * Método estático que muestra todos los juegos realizados en el programa.
     */
    public static void mostrarJuegos() {
        for (Juego j : juegos) {
            System.out.println(j);
        }
    }

    /*
    * Método estático que muestra todos los juegos realizados en el programa de
    * los estudiantes de un paralelo recibido.
    *
    * @param posicionParalelo El parámetro posicionParalelo es la posición del paralelo en una lista de todas los paralelos cargados en el programa
     */
    public static void mostrarJuegos(int posicionParalelo) {
        System.out.println("REPORTES DEL PARALELO " + paralelos.get(posicionParalelo));
        for (Juego j : juegos) {
            if (j.getParalelo().equals(paralelos.get(posicionParalelo))) {
                System.out.println(j);
            }
        }
    }

    /*
    * Método estático que muestra una lista de todas las preguntas de una 
    * materia recibida.
    *
    * @param posicionMateria El parámetro posicionMateria es la posición de la materia en una lista de todas las materias cargadas en el programa
     */
    public static void mostrarPreguntasMateria(int posicionMateria) {
        int i = 1;
        for (Pregunta p : materias.get(posicionMateria).getPreguntas()) {
            System.out.println(i + ". " + p);
            i++;
            for (Opcion op : p.getOpciones()) {
                System.out.println(op);
            }
            System.out.println();
        }
    }

    /*
    * Método estático que permite al usuario escoger una materia y ver todas sus
    * preguntas.
    * Devuelve la posición de la materia escogida.
    * 
    * @return La posición de la materia escogida
     */
    public static int visualizarPreguntas() {
        Scanner sc = new Scanner(System.in);

        mostrarMaterias();

        // Selección de una materia
        int posicionMateria;
        do {
            System.out.println("Seleccione una materia (0 para regresar): ");
            posicionMateria = sc.nextInt() - 1;
            if (posicionMateria < -1 || posicionMateria > materias.size() - 1) {
                System.out.println("Materia no encontrada\n");
            }
        } while (posicionMateria < -1 || posicionMateria > materias.size() - 1); // Validación de que la materia escogida exista

        // Verificación de que la materia escogida exista
        if (posicionMateria != -1) {
            if (materias.get(posicionMateria).getPreguntas().isEmpty()) {
                System.out.println("No existen preguntas para " + materias.get(posicionMateria) + "\n"); // Verificación de que existan preguntas en la materia escogida
            } else {
                mostrarPreguntasMateria(posicionMateria); // Muestra la lista de las preguntas de la materia escogida
            }
        }

        System.out.println();

        return posicionMateria;
    }

    /*
    * Método estático que permite al usuario agregar el enunciado, el nivel y 
    * las opciones de una pregunta a una materia escogida.
     */
    public static void agregarPregunta() {
        Scanner sc = new Scanner(System.in);

        mostrarMaterias();

        // Elección de una materia
        int posMateria;
        do {
            System.out.println("Escoja la materia de la lista de materias (0 para regresar): ");
            posMateria = sc.nextInt() - 1;
            if (posMateria < -1 || posMateria > materias.size() - 1) {
                System.out.println("ESTA MATERIA NO ESTÁ REGISTRADA\n");
            }
        } while (posMateria < -1 || posMateria > materias.size() - 1); // Validación de que la materia escogida exista

        // Verificación de que la materia escogida exista
        if (posMateria != -1) {
            sc.nextLine();
            System.out.println("<<Accediendo a agregar pregunta>>");

            // Ingreso de enunciado de la pregunta
            System.out.println("Ingrese enunciado para su pregunta: ");
            String enunciadoIngr = sc.nextLine();
            System.out.println();

            // Ingreso del nivel de la pregunta
            int nivelIngr;
            do {
                System.out.println("Se han establecido " + materias.get(posMateria).getNumeroNiveles() + " niveles para esta materia");
                System.out.println("Ingrese el nivel de dificultad para su pregunta: ");
                nivelIngr = sc.nextInt();
                if (nivelIngr < 1 || nivelIngr > materias.get(posMateria).getNumeroNiveles()) {
                    System.out.println("Nivel incorrecto\n");
                }
            } while (nivelIngr < 1 || nivelIngr > materias.get(posMateria).getNumeroNiveles()); // Validación de que el nivel de la pregunta no sea menor que 1 o mayor que la cantidad de niveles de la materia seleccionada
            sc.nextLine();
            System.out.println();

            // Ingreso de las cuatro opciones de la pregunta
            Opcion[] opciones = new Opcion[4];
            String textoOpcion;
            for (int i = 0; i < 4; i++) {
                Respuesta respuesta;
                if (i == 0) {
                    // Ingreso de la respuesta correcta de la pregunta
                    System.out.println("Ingrese la respuesta correcta:");
                    respuesta = Respuesta.CORRECTO;
                } else {
                    // Ingreso de las tres posibles respuestas de la pregunta
                    System.out.println("Ingrese una posible respuesta:");
                    respuesta = Respuesta.INCORRECTO;

                }
                textoOpcion = sc.nextLine();
                System.out.println();

                // Agregación de las respuestas a las opciones de la pregunta
                opciones[i] = new Opcion(textoOpcion, respuesta);
            }

            // Agregación de la pregunta a la materia seleccionada
            materias.get(posMateria).getPreguntas().add(new Pregunta(enunciadoIngr, nivelIngr, opciones));

            System.out.println("Pregunta agregada correctamente");
        }
        System.out.println();

    }

    /*
    * método estático que permite al usuario escoger una pregunta de una materia
    * seleccionada y eliminarla.
     */
    public static void eliminarPregunta() {
        int posicionMateria = visualizarPreguntas();

        // Verificación de que existan preguntas en la materia escogida
        if (posicionMateria != -1) {
            Scanner sc = new Scanner(System.in);

            // Selección de pregunta
            int posPregunta;
            do {
                System.out.print("Seleccione un pregunta a eliminar (0 para regresar): ");
                posPregunta = sc.nextInt() - 1;
                if (posPregunta < -1 || posPregunta > materias.get(posicionMateria).getPreguntas().size() - 1) {
                    System.out.println("Pregunta no existente\n");
                }
            } while (posPregunta < -1 || posPregunta > materias.get(posicionMateria).getPreguntas().size() - 1); // Validación de que la pregunta seleccionada exista

            // Verificación de que la pregunta escogida exista
            if (posPregunta != -1) {
                // Eliminación de la pregunta escogida
                materias.get(posicionMateria).getPreguntas().remove(posPregunta);
                System.out.println("Pregunta eliminado correctamente");
            }

            System.out.println();
        }
    }

    /*
    * Método estático que carga información (un término académico, una materia 
    con sus preguntas un paralelo con sus estudiantes y un cuestionario, etc) 
    * por defecto al iniciar el programa.
     */
    public static void CargarInformacion() {
        // Creción de 33 estudiantes
        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        estudiantes.add(new Estudiante("202210712", "BORBOR GUTIERREZ VICTOR DANIEL", "vicbguti@espol.edu.ec"));
        estudiantes.add(new Estudiante("202208880", "MEJIA PARRA KEVIN SANTIAGO", "kesameji@espol.edu.ec"));
        estudiantes.add(new Estudiante("202211355", "ARAUJO ORTEGA DIEGO ENZO JAVIER", "dienarau@espol.edu.ec"));
        estudiantes.add(new Estudiante("202006086", "CABRERA VIVANCO ALVARO DAVID", "alvdcabr@espol.edu.ec"));
        estudiantes.add(new Estudiante("202110136", "ACELDO TORRES MARIA GRAZIA", "maactorr@espol.edu.ec"));
        estudiantes.add(new Estudiante("202108643", "AGUILAR TINOCO JEAN CARLOS", "jcaguila@espol.edu.ec"));
        estudiantes.add(new Estudiante("202111928", "AMORETTI SANCHEZ JUAN CARLOS", "@espol.edu.ec"));
        estudiantes.add(new Estudiante("202105946", "ANDRADE VELASCO ANGELLO BERNIE", "jamorett@espol.edu.ec"));
        estudiantes.add(new Estudiante("202104816", "AZU PERLAZA NICOLE FERNANDA", "nfazu@espol.edu.ec"));
        estudiantes.add(new Estudiante("202110219", "BALDEON BAQUE IVAN GONZALO", "ivagbald@espol.edu.ec"));
        estudiantes.add(new Estudiante("202113056", "BARBERAN GALLARDO MELISSA ESTEFANIA", "melesbar@espol.edu.ec"));
        estudiantes.add(new Estudiante("202109328", "BASILIO ACEBO DANIELA MILENA", "dmbasili@espol.edu.ec"));
        estudiantes.add(new Estudiante("202113049", "CORDERO CALLES RONALD ELIAS", "rcordero@espol.edu.ec"));
        estudiantes.add(new Estudiante("202010278", "ESPINOZA PINELA ANGELO ALEXANDER", "angepine@espol.edu.ec"));
        estudiantes.add(new Estudiante("202108288", "GONZABAY ESPIN DOUGLAS VICENTE", "dvgonzab@espol.edu.ec"));
        estudiantes.add(new Estudiante("202100772", "GUAMAN QUIJIJE RONALD STEVEN", "rsguaman@espol.edu.ec"));
        estudiantes.add(new Estudiante("202208302", "HERRERA LEON ANTHONY ARTURO", "anthleon@espol.edu.ec"));
        estudiantes.add(new Estudiante("202202552", "LINO INDACOCHEA STEVEN MOISES", "stemlino@espol.edu.ec"));
        estudiantes.add(new Estudiante("202212965", "LORENZO LOPEZ ERICK GABRIEL", "erillope@espol.edu.ec"));
        estudiantes.add(new Estudiante("201405946", "MACIAS ARTURO LEONARDO DAVID", "leodamac@espol.edu.ec"));
        estudiantes.add(new Estudiante("202001244", "MAZA PUNINE ISSAC ALEXANDER", "issamaza@espol.edu.ec"));
        estudiantes.add(new Estudiante("202211306", "NAVARRETE CASTILLO ANTHONY JOSUE", "annacast@espol.edu.ec"));
        estudiantes.add(new Estudiante("202207726", "POVEDA QUIMIZ MICHAEL CRESCENCIO", "mcpoveda@espol.edu.ec"));
        estudiantes.add(new Estudiante("202207924", "RIVAS ABAD BRAULIO DE JESUS", "brarabad@espol.edu.ec"));
        estudiantes.add(new Estudiante("202111589", "RIVAS PINCAY EMMANUEL GERARDO", "egrivas@espol.edu.ec"));
        estudiantes.add(new Estudiante("202203428", "ROMERO ALMEIDA EMILIO JOSE", "emjorome@espol.edu.ec"));
        estudiantes.add(new Estudiante("202111910", "SANTANDER LOPEZ EDU ISRAEL", "eduisant@espol.edu.ec"));
        estudiantes.add(new Estudiante("201417520", "SUAREZ MENDIETA GARY STEVEN", "gssuarez@espol.edu.ec"));
        estudiantes.add(new Estudiante("202205324", "SUAREZ VALDIVIESO JOSE JULIO", "jojusuar@espol.edu.ec"));
        estudiantes.add(new Estudiante("202107645", "VARGAS ISA GENESIS DAYANNA", "gdvargas@espol.edu.ec"));
        estudiantes.add(new Estudiante("202109229", "VILLAMAGUA ESCUDERO JUAN MATEO", "juamvill@espol.edu.ec"));
        estudiantes.add(new Estudiante("202106050", "ZAMORA CEDEÑO JORDY STEVEN", "jszamora@espol.edu.ec"));
        estudiantes.add(new Estudiante("202208260", "ZARUMA GAME JOSHUA ANDRES", "jazaruma@espol.edu.ec"));

        // Creación y agregación de un término académico a la lista terminos y terminoJuego
        TerminoAcademico ta = new TerminoAcademico("2023", 1);
        terminos.add(ta);
        terminoJuego = ta;

        // Creación y agregación de preguntas a una lista pre
        ArrayList<Pregunta> pre = new ArrayList<Pregunta>();

        pre.add(new Pregunta("Con que palabra se crea una clase", 1, new Opcion[]{
            new Opcion("Class", Respuesta.INCORRECTO),
            new Opcion("Clase", Respuesta.INCORRECTO),
            new Opcion("class", Respuesta.CORRECTO),
            new Opcion("clas", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("Que es POO", 1, new Opcion[]{
            new Opcion("Programacion orientada a objetos", Respuesta.CORRECTO),
            new Opcion("Problemas orientados a osos", Respuesta.INCORRECTO),
            new Opcion("Paradigma oriental de objetos", Respuesta.INCORRECTO),
            new Opcion("Presentacion occidental de ornitorincos", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("Qué línea de código se utiliza para importar ArrayList?", 1, new Opcion[]{
            new Opcion("import java.util.Stack", Respuesta.INCORRECTO),
            new Opcion("package java.util.Scanner", Respuesta.INCORRECTO),
            new Opcion("import java.util.ArrayList", Respuesta.CORRECTO),
            new Opcion("package java.util.ArrayList", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("Como se llama el compilador de java", 2, new Opcion[]{
            new Opcion("Java", Respuesta.INCORRECTO),
            new Opcion("JavaC", Respuesta.CORRECTO),
            new Opcion("JVM", Respuesta.INCORRECTO),
            new Opcion(".Jar", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("Que elementos corresponden a la firma de un metodo", 2, new Opcion[]{
            new Opcion("Tipo de retorno, nombre, parametros", Respuesta.INCORRECTO),
            new Opcion("modificador de acceso, tipo de retorno, nombre", Respuesta.INCORRECTO),
            new Opcion("modificador de acceso, nombre", Respuesta.INCORRECTO),
            new Opcion("nombre, parametros", Respuesta.CORRECTO)}));

        pre.add(new Pregunta("Cuáles son los modificadores de acceso?", 2, new Opcion[]{
            new Opcion("public, protected, private y default", Respuesta.CORRECTO),
            new Opcion("public, protected, private y friend", Respuesta.INCORRECTO),
            new Opcion("public, protected, private e internal", Respuesta.INCORRECTO),
            new Opcion("public, protected, private y  package-private plus", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("Cual es el valor por defecto de un arreglo de caracteres", 3, new Opcion[]{
            new Opcion("\\u000", Respuesta.CORRECTO),
            new Opcion("null", Respuesta.INCORRECTO),
            new Opcion("\\null", Respuesta.INCORRECTO),
            new Opcion("''", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("Cual es el tipo de retorno del metodo hashCode", 3, new Opcion[]{
            new Opcion("Object", Respuesta.INCORRECTO),
            new Opcion("boolean", Respuesta.INCORRECTO),
            new Opcion("int", Respuesta.CORRECTO),
            new Opcion("HashMap", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("Qué palabra reservada se utiliza para llamar en una clase hija a un atributo o método de una clase padre?", 2, new Opcion[]{
            new Opcion("father", Respuesta.INCORRECTO),
            new Opcion("super", Respuesta.CORRECTO),
            new Opcion("parent", Respuesta.INCORRECTO),
            new Opcion("this", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("¿Cuales son los 4 pilares fundamentales de la programacion orientada a objetos?", 3, new Opcion[]{
            new Opcion("Abstraccion, Encapsulamiento, Polimorfismo, Transferencia", Respuesta.INCORRECTO),
            new Opcion("Abstraccion, Capsulamiento, Polimorfismo, Herencia", Respuesta.INCORRECTO),
            new Opcion("Abstraccion, Encapsulamiento, Polimorfismo, Herencia", Respuesta.CORRECTO),
            new Opcion("Atraccion, Encapsulamiento, Polimorfismo, Herencia", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("¿Cual es la estructura para que una subclase publica herede de una superclase?", 2, new Opcion[]{
            new Opcion("public class Subclase extern Superclase", Respuesta.INCORRECTO),
            new Opcion("publica class Subclase extends Superclase", Respuesta.INCORRECTO),
            new Opcion("public class Superclase extends Subclase", Respuesta.INCORRECTO),
            new Opcion("public class Subclase extends Superclase", Respuesta.CORRECTO)}));

        pre.add(new Pregunta("¿Cómo escribiría el atributo privado nombre de tipo String en un diagrama UML?", 2, new Opcion[]{
            new Opcion(" + nombre: String", Respuesta.INCORRECTO),
            new Opcion(" - String: nombre", Respuesta.INCORRECTO),
            new Opcion(" - nombre: String", Respuesta.CORRECTO),
            new Opcion(" - nombre; String", Respuesta.INCORRECTO)}));

        // Creación y agregación de una materia a la lista materias
        Materia ma = new Materia("CCPG1052", "POO", 3, pre);
        materias.add(ma);
        ta.addMaterias(ma);

        // Creación y agregación de un paralelo con sus estudiantes a la lista paralelos
        Paralelo pa = new Paralelo(3, ta, ma, estudiantes);
        paralelos.add(pa);
        ma.AgregarParalelo(pa);

        // Ordenación de preguntas por su nivel
        Collections.sort(pre, new OrdenPregunta());

        // Agregación de un cuestionario a la lista cuestionarios
        cuestionarios.add(new Cuestionario(
                new Comodin[]{new Comodin("50/50"), new Comodin("Llamada al apoyo"), new Comodin("Pregunta al publico")},
                pre));
        
        SerializarArchivos();
        
        Mostrar();

    }
    public static void SerializarArchivos() {
        String path = "C:\\Users\\Usuario\\OneDrive\\Documentos\\ProyectoPoo\\ProyectoPoo\\App\\src\\main\\resources\\archivos\\";
        for (TerminoAcademico ta : terminos) {
            try ( ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream(
                    path
                    + ta.getAnio()
                    + "-"
                    + String.valueOf(ta.getNumeroTermino()
                            + ".ser")))) {
                ob.writeObject(ta);
                ob.flush();
            } catch (IOException e) {
                System.out.println("Malio sal");
                e.printStackTrace();
            }
        }

    }
    
    public static void Mostrar(){
         TerminoAcademico ta;
        String path = "C:\\Users\\Usuario\\OneDrive\\Documentos\\ProyectoPoo\\ProyectoPoo\\App\\src\\main\\resources\\archivos\\2023-1.ser";
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))){
            ta = (TerminoAcademico) in.readObject();
            System.out.println(ta);
            System.out.println("");
            
            for (Materia ma : ta.getMaterias()){
                System.out.println(ma);
                System.out.println("");
                for (Pregunta pre : ma.getPreguntas()){
                    System.out.println(pre);
                }
                for (Paralelo pa : ma.getParalelos()){
                    System.out.println(pa);
                }
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
